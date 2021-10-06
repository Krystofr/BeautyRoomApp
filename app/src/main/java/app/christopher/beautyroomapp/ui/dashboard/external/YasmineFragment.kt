package app.christopher.beautyroomapp.ui.dashboard.external

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_yasmine.*
import java.text.SimpleDateFormat
import java.util.*

class YasmineFragment : Fragment() {
    private var cal = GregorianCalendar()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yasmine, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Create an OnDateSetListener
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.YEAR, year)
            updateDateInView()
        }

        time_picker_barb.setOnClickListener { setBarbTime() }
        time_picker_beard.setOnClickListener { setBeardTime() }
        time_picker_conditioning.setOnClickListener { setConditioningTime() }
        time_picker_facial_male.setOnClickListener { setMaleFacialsTime() }
        date_picker_formen.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
                .show()
        }
    }

    private fun setMaleFacialsTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker_facial_male!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }

    private fun setConditioningTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker_conditioning!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }

    private fun setBeardTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker_beard!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }
    private fun setBarbTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker_barb!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date_picker_formen!!.text = sdf.format(cal.time)
    }
}