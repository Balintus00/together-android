package hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.together.R
import hu.bme.aut.android.together.databinding.FragmentDateSetterBinding
import java.util.*

/**
 * This [Fragment] provides an user interface, that can be used by the user to set the dates of
 * the event which is under creation (beginning & end). Clicking the TextViews, that are containing
 * information about the date and time of the event will pop up and [TimePickerDialog] or [DatePickerDialog]
 * which the user can use to specify the date and time of the event.
 */
class DateSetterFragment : Fragment() {

    private lateinit var binding: FragmentDateSetterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDateSetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUIBehaviour()
    }

    private fun setUIBehaviour() {
        setFromDateBehaviour()
        setFromTimeBehaviour()
        setToDateBehaviour()
        setToTimeBehaviour()
    }

    private fun setFromDateBehaviour() {
        binding.tvAddEventFromDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.tvAddEventFromDate.text =
                        getString(R.string.date_year_month_day, year, month, day)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setFromTimeBehaviour() {
        binding.tvAddEventFromHourMinute.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    binding.tvAddEventFromHourMinute.text =
                        getString(R.string.time_hour_minute, hour, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setToDateBehaviour() {
        binding.tvAddEventToDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.tvAddEventToDate.text =
                        getString(R.string.date_year_month_day, year, month, day)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setToTimeBehaviour() {
        binding.tvAddEventToHourMinute.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    binding.tvAddEventToHourMinute.text =
                        getString(R.string.time_hour_minute, hour, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}