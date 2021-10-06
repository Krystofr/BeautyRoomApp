package app.christopher.beautyroomapp

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var firebaseAuth: FirebaseAuth? = null
    private val TAG = "LoginFragment"
    private lateinit var dialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }
override fun onStart() {
    super.onStart()
    if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
        findNavController().navigate(R.id.action_loginFragment_to_bottomNav)
    }
}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(requireContext())

        btn_reset_pwd.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
        btn_login.setOnClickListener {
            loginUser()
        }

        txt_to_signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun loginUser() {
        val loginEmail: String = login_email.text!!.toString()
        val loginPassword: String = login_pwd.text!!.toString()

        if (TextUtils.isEmpty(loginEmail)) {
            Toast.makeText(requireContext(), "Please enter your Email", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(loginPassword)) {
            Toast.makeText(requireContext(), "Please enter your Password", Toast.LENGTH_SHORT).show()
        } else {
            dialog.setTitle("Logging in")
            dialog.show()
            firebaseAuth!!.signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dialog.cancel()
                        Toast.makeText(requireContext(), "Logged In!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_bottomNav)
                    } else {
                        dialog.cancel()
                        Toast.makeText(requireContext(), task.exception!!.message, Toast.LENGTH_SHORT).show()
                        task.exception!!.message?.let {
                            Log.d(TAG, it)
                        }
                    }
                }
        }
        }

    }