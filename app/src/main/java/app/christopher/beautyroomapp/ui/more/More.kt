package app.christopher.beautyroomapp.ui.more

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import app.christopher.beautyroomapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_more.*

class More : Fragment(R.layout.fragment_more) {
    private val profileDBInstance = Firebase.firestore
    private val firebaseAuth: FirebaseAuth? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_save_profile_changes.setOnClickListener {
            saveProfileChanges()
        }
    }

    private fun saveProfileChanges() {
        val userPhone = phone_number.text.toString()
        val emailAddress = email.text.toString()
        val userNames = names.text.toString()
        val userAddress = address.text.toString()

        if (TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(emailAddress)
                || TextUtils.isEmpty(userNames) || TextUtils.isEmpty(userAddress)) {
            Toast.makeText(requireContext(), "Please fill in all information", Toast.LENGTH_LONG).show()
        }  else if (userPhone.length < 11) {
            phone_number.error = "Invalid: Number too short!"
        }  else {
            val profileDetails = hashMapOf<String, Any>(
                    "Phone Number" to userPhone,
                    "Email ID" to emailAddress,
                    "Names" to userNames,
                    "Address" to userAddress)

            profileDBInstance
                    .collection("Profile Update")
                    .document(firebaseAuth!!.uid.toString())
                    .set(profileDetails)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Changes Saved!", Toast.LENGTH_LONG).show()
                            //finish
                            findNavController().navigate(R.id.action_navigation_more_to_navigation_dashboard)
                        } else {
                            Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_LONG).show()
                            task.exception!!.message?.let {
                                // Log.d(TAG, it)
                            }
                        }
                    }
        }
    }

}