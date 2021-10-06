package app.christopher.beautyroomapp.ui.dashboard.external

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.christopher.beautyroomapp.R
import app.christopher.beautyroomapp.model.PaymentsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_payment.*
import java.util.*

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private val args: PaymentFragmentArgs by navArgs()
    private val TAG = "PaymentFragment"

    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog : ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth
        firestoreDB = FirebaseFirestore.getInstance()
        progressDialog = ProgressDialog(requireContext())

        rec_service.text = args.serviceNameRecipient
        rec_time.text = args.timeRecipient
        rec_duration.text = args.periodRecipient
        rec_price.text = args.priceRecipient
        txt_receiver_total.text = args.priceRecipient

        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        card_exp.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), { view, year, month, dayOfMonth ->
                card_exp.setText(""+dayOfMonth+ "/" +month+ "/" +year)
            }, dayOfWeek, monthOfYear, year)
            datePickerDialog.show()
        }

        cv_purchase.setOnClickListener {
            writeToCloudFirestore()
        }
    }

    private fun writeToCloudFirestore() {
        val cardName = holder_name!!.editableText.toString()
        val cardNumber = card_number!!.editableText.toString()
        val cardExp = card_exp!!.editableText.toString()
        val cardCVV = card_cvv!!.editableText.toString()

        val serviceName = rec_service?.text.toString()
        val serviceTime = rec_time?.text.toString()
        val serviceDuration = rec_duration?.text.toString()
        val servicePrice = rec_price?.text.toString()
        
        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(requireContext(), "Please enter the CardHolder name.", Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(cardNumber)) {
            Toast.makeText(requireContext(), "Card Number required", Toast.LENGTH_SHORT).show()
        } else if (cardNumber!!.length < 16 ) {
            Toast.makeText(requireContext(), "Card number too short. 16 digits required", Toast.LENGTH_SHORT).show()
            card_number.error = "Incorrect card number. Must be 16 digits long"
        }
        else if (cardNumber!!.length > 16 ) {
            Toast.makeText(requireContext(), "Card number too long. 16 digits required", Toast.LENGTH_SHORT).show()
            card_number.error = "Incorrect card number. Must be 16 digits long"
        }
        else if (TextUtils.isEmpty(cardExp)) {
            Toast.makeText(requireContext(), "Card expiry date required", Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(cardCVV)) {
            Toast.makeText(requireContext(), "Card CVV required", Toast.LENGTH_SHORT).show()
        }
        else {
            progressDialog.setTitle("Processing...")
            progressDialog.show()
            //Gets data from the Model class
            val bookingsObject = PaymentsModel(serviceName, serviceTime, serviceDuration, servicePrice)
            //Write to Database when user purchases a Service
            firestoreDB
                .collection("Feedback")
                .document(firebaseAuth.currentUser?.uid.toString())
                .set(bookingsObject)
                //Sets an OnCompleteListener on the firebase task object.
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.cancel()
                       successDialog()
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_LONG).show()
                        task.exception!!.message?.let {
                            Log.d(TAG, it)
                        }
                    }
                }
        }
    }
    private fun successDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setCancelable(false)
            .setMessage("Your Booking and Payment has been successfully processed")
            .setTitle("Booking & Payment Successful")
            .setPositiveButton("PROCEED"){dialogInterface, which, ->
                findNavController().navigate(R.id.action_paymentFragment_to_navigation_dashboard)
            }
            .show()
    }
}