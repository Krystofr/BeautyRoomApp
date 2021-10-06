package app.christopher.beautyroomapp.ui.dashboard.external

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.christopher.beautyroomapp.R
import app.christopher.beautyroomapp.config.Config
import app.christopher.beautyroomapp.model.BookingsModel
import app.christopher.beautyroomapp.ui.dashboard.external.activity.PaymentDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.paypal.android.sdk.payments.*
import kotlinx.android.synthetic.main.fragment_kristiana.*
import org.json.JSONException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class KristianaFragment : Fragment(R.layout.fragment_kristiana) {
    var cal = GregorianCalendar()
    //Firebase Firestore reference object
    private lateinit var bookingsDB: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var amount : String

    val PAYPAL_REQUEST_CODE : Int = 7171
    private val config : PayPalConfiguration = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId(Config.PAYPAL_CLIENT_ID)

    override fun onDestroy() {
        activity?.stopService(Intent(requireContext(), PayPalService::class.java))
        super.onDestroy()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Start paypal service
        val intent = Intent(requireContext(), PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        activity?.startService(intent)
        cv_lashes.setOnClickListener {
            processPaypalPayment()
            bookAppointment()
        }

        //Create an OnDateSetListener
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
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
                    cal.get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }

        time_picker1.setOnClickListener {
            setTime()
        }
        time_picker1_nails.setOnClickListener {
            setNailsTime() }
        time_picker1_nails_care.setOnClickListener { setNailsCareTime() }
        time_picker1_hair.setOnClickListener {
            setHairTime()
        }
    }

    private fun processPaypalPayment() {
        amount = txt_price.text.toString()
        val payPalPayment = PayPalPayment(
                BigDecimal(amount),
                "GBP",
                "Eyelashes Service Payment",
                PayPalPayment.PAYMENT_INTENT_SALE
        )
        val dialogBox = AlertDialog.Builder(requireContext())
        dialogBox.setCancelable(false)
        dialogBox.setTitle("CONFIRMATION!")
        dialogBox.setMessage("Proceed to payment with PAYPAL?")
        dialogBox.setPositiveButton("YES"){ _, _ ->
            val intent = Intent(requireContext(), PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment)
            startActivityForResult(intent, PAYPAL_REQUEST_CODE)
        }
        dialogBox.setNegativeButton("CANCEL"){action, _ ->
            action.dismiss()
        }
        dialogBox.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val paymentConfirmation: PaymentConfirmation = data!!.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)!!
                if (paymentConfirmation != null) {
                    try {
                        val paymentDetails : String = paymentConfirmation.toJSONObject().toString(4)
                        startActivity(Intent(requireContext(), PaymentDetails::class.java)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount))

                    } catch (e: JSONException) {
                        e.printStackTrace()
                        e.message!!
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(requireContext(), "Transaction Cancelled!", Toast.LENGTH_SHORT).show()
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(requireContext(), "Invalid status!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun bookAppointment() {
        val serviceName = txt_lashes?.text.toString()
        val serviceTime = time_picker1?.text.toString()
        val serviceDate = date_picker?.text.toString()
        val servicePrice = txt_price?.text.toString()

        when {
            TextUtils.isEmpty(serviceDate) -> {
                Toast.makeText(requireContext(), "Please pick a date", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(serviceTime) -> {
                Toast.makeText(
                        requireContext(),
                        "Please select a suitable time",
                        Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                promptDialog()
                val booking = BookingsModel(serviceName, servicePrice, serviceTime, serviceDate)
                bookingsDB.collection(firebaseAuth.currentUser!!.email.toString()).document().set(
                        booking
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                    requireContext(),
                                    task.exception!!.message,
                                    Toast.LENGTH_LONG
                            ).show()
                            task.exception!!.message?.let {
                                Log.d(TAG, it)
                            }
                        }
                    }
            }
        }
    }

    private fun promptDialog() {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        dialog.setCancelable(false)
            .setTitle("Please Confirm!")
            .setMessage("Are you sure about this service?")
            .setPositiveButton("PROCEED"){ dialog, which ->
                findNavController().navigate(R.id.action_paymentFragment_to_navigation_dashboard)
            }
            .show()
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
                requireContext(),
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
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

    private fun setHairTime() {
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
    companion object {
        //private lateinit var navController: NavController
        //private lateinit var dialog: ProgressDialog
        private const val TAG = "KristianaFragment"
    }
}
