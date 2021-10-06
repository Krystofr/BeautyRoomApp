package app.christopher.beautyroomapp

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_reset_password.*

class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password) {

    private lateinit var auth: FirebaseAuth
    private val TAG = "ResetPasswordFragment"
    private lateinit var progress : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = ProgressDialog(requireContext())
        btn_reset.setOnClickListener {
            resetUserPassword()
        }
        back_to_login.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }
    }

    private fun resetUserPassword() {
        val emailReset: String = reset_email.text!!.toString()
        if (TextUtils.isEmpty(emailReset)) {
            errorDialog()
        } else {
            progress.setTitle("Sending...")
            progress.show()
            Firebase.auth.sendPasswordResetEmail(emailReset)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            progress.cancel()
                           showDialog()
                        } else {
                            progress.dismiss()
                            Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_SHORT).show()
                            task.exception!!.message?.let {
                                Log.d(TAG, it)
                            }
                        }
                    }
        }

    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Confirmation")
        dialog.setMessage("A password reset email has been sent to your inbox")
        dialog.setCancelable(false)
        dialog.setPositiveButton("OKAY"){dialogInterface, which ->
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }
        dialog.setNegativeButton("CANCEL"){dialog_, which_ ->
            dialog_.cancel()
        }
        dialog.show()
    }
    private fun errorDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage("Please enter the email associated with •Xpression Hair & Beauty•")
        dialog.setCancelable(false)
        dialog.setPositiveButton("OKAY"){dialogInterface, which ->
           dialogInterface.dismiss()
        }
        dialog.show()
    }
}