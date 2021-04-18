package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.databinding.FragmentEventListBinding
import hu.bme.aut.android.together.features.currentevents.viewmodel.ComingEventListViewModel
import hu.bme.aut.android.together.features.currentevents.viewmodel.EventListLoaded
import hu.bme.aut.android.together.features.currentevents.viewmodel.EventListState
import hu.bme.aut.android.together.features.currentevents.viewmodel.Loading
import hu.bme.aut.android.together.features.shared.eventlist.adapter.EventListAdapter
import hu.bme.aut.android.together.model.presentation.EventShortInfo

/**
 * This Fragment contains the list of the user's current events, that will happen in the future.
 */
@AndroidEntryPoint
class ComingEventListFragment : RainbowCakeFragment<EventListState, ComingEventListViewModel>() {

    //TODO this data mocking will be removed later
    companion object {
        private val FAKE_PROFILE_ID = 1L

        private val eventDetailsItemOptionsArray = arrayOf(
            arrayOf(false, true, false, true),
            arrayOf(false, false, true, false),
            arrayOf(true, false, true, true)
        )
    }

    private lateinit var eventListAdapter: EventListAdapter

    private lateinit var binding: FragmentEventListBinding

    private val comingEventListViewModel : ComingEventListViewModel by viewModels()

    override fun provideViewModel(): ComingEventListViewModel  = comingEventListViewModel

    override fun render(viewState: EventListState) {
        when(viewState) {
            is Loading -> {
                binding.cpiEventListLoading.isVisible = true
                binding.flContent.isVisible = false
            }
            is EventListLoaded -> {
                binding.cpiEventListLoading.isVisible = false
                setUpUIOnLoaded(viewState.eventShortInfoList)
                binding.flContent.isVisible = true
            }
        }.exhaustive
    }

    private fun setUpUIOnLoaded(eventShortInfoList: List<EventShortInfo>) {
        eventListAdapter.submitList(eventShortInfoList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFabBehaviour()
        initRecyclerView()
    }

    /**
     * Initializes the contained RecyclerView widget's adapter and layoutManager.
     * [LinearLayoutManager] is used as its layoutManager, and the adapter is set to an [EventListAdapter]
     * instance. When an item event item is clicked, the user should be navigated to a
     * [hu.bme.aut.android.together.features.eventdetails.fragment.details.EventDetailsFragment]
     * instance. This behaviour is passed as a method reference in the adapter's constructor.
     */
    private fun initRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(requireContext())
            eventListAdapter = EventListAdapter { position ->
                eventDetailsItemOptionsArray[position].let { optionsArray ->
                    EventListsPagerFragmentDirections.actionCurrentEventsListFragmentToEventDetailsFragment(
                        isOrganiser = optionsArray[0],
                        isPrivate = optionsArray[1],
                        isParticipantCountLimited = optionsArray[2],
                        isParticipant = optionsArray[3]
                    )
                        .let { action ->
                            findNavController().navigate(action)
                        }
                }
            }
            adapter = eventListAdapter
        }
    }

    /**
     * The FAB on this Fragment can be used to navigate to the event adding screen, to an
     * [hu.bme.aut.android.together.features.addevent.fragment.AddEventPagerFragment] instance.
     */
    private fun setFabBehaviour() {
        binding.eventListAddFab.setOnClickListener {
            EventListsPagerFragmentDirections.actionCurrentEventsListFragmentToAddEventPagerFragment()
                .let { action ->
                    findNavController().navigate(action)
                }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadComingEventShortInfoListByProfileId(FAKE_PROFILE_ID)
    }
}