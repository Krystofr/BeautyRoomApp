package app.christopher.beautyroomapp.ui.dashboard.external

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_specialists.*
import java.text.SimpleDateFormat
import java.util.*

class SpecialistsFragment : Fragment(R.layout.fragment_specialists){
    //private lateinit var navController : NavController

    var cal = GregorianCalendar()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.YEAR, year)
                updateDateInView()
            }
        date_picker!!.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
                .show()
        }
        time_picker1.setOnClickListener {
            setTime()
        }
        time_picker1_nails.setOnClickListener {
            setNailsTime() }
        time_picker1_nails_care.setOnClickListener { setNailsCareTime() }
        time_picker1_makeup.setOnClickListener { setMakeupTime() }
    }

    private fun setMakeupTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker1_makeup!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
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
        date_picker!!.text = sdf.format(cal.time)
    }
    private fun setTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker1!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }
    private fun setNailsTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker1_nails!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }
    private fun setNailsCareTime() {
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            //Set time to Textview
            time_picker1_nails!!.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }
        TimePickerDialog(
            requireContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                Calendar.MINUTE
            ), true
        ).show()
    }
}