package hu.bme.aut.android.together.features.currentevents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.android.together.databinding.FragmentCurrentEventsListsContainerBinding
import hu.bme.aut.android.together.features.currentevents.adapter.EventTimeAdapter

class CurrentEventsListsContainerFragment : Fragment() {

    private lateinit var binding: FragmentCurrentEventsListsContainerBinding
    private lateinit var pagerAdapter: EventTimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentEventsListsContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventTimePagerAdapter()
        attachEventTimeTabLayoutMediator()
    }

    private fun setEventTimePagerAdapter() {
        pagerAdapter = EventTimeAdapter(this)
        binding.eventTimePager.adapter = pagerAdapter
    }

    private fun attachEventTimeTabLayoutMediator() {
        TabLayoutMediator(binding.eventTimeTabLayout, binding.eventTimePager) { tab, position ->
            tab.text = pagerAdapter.getTabName(position)
        }.attach()
    }
}