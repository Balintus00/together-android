package hu.bme.aut.android.together.features.addevent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentVisibilityChooserBinding

class VisibilityChooserFragment : Fragment() {

    private lateinit var binding: FragmentVisibilityChooserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisibilityChooserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCardClickBehaviour()
    }

    private fun setCardClickBehaviour() {
        with(binding) {
            cardPrivate.setOnClickListener {
                VisibilityChooserFragmentDirections.actionVisibilityChooserFragmentToCategoryPickerFragment()
                    .let { action ->
                        findNavController().navigate(action)
                    }
            }
            cardPublic.setOnClickListener {
                VisibilityChooserFragmentDirections.actionVisibilityChooserFragmentToPublicEventRuleSetterFragment()
                    .let { action ->
                        findNavController().navigate(action)
                    }
            }
        }
    }

}