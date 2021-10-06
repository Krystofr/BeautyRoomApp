package app.christopher.beautyroomapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.paypal.android.sdk.payments.PayPalConfiguration
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val TAG: String = "SignUpFragment"
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var dialog : ProgressDialog
    private val PAYPAL_REQUEST_CODE : Int = 7171
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val reqCode: Int = 123

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
            findNavController().navigate(R.id.action_signUpFragment_to_bottomNav)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign In inside onCreate mentod
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // getting the value of gso inside the GoogleSigninClient
              mGoogleSignInClient= GoogleSignIn.getClient(requireContext(),gso)
        firebaseAuth = Firebase.auth
    }

    private fun googleSignIn(){
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,reqCode)
    }
    /*
    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_signUpFragment_to_bottomNav)
        }
    }
*/
     //onActivityResult() function : this is where we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==reqCode){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }


    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            }
        } catch (e: ApiException){
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    // UpdateUI() function - this is where we specify what UI updates are needed after google signin has taken place.
    private fun updateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
                SavedPreference.setEmail(requireContext(),account.email!!.toString())
                SavedPreference.setUsername(requireContext(), account.displayName!!.toString())
                //val intent = Intent(requireContext(), BottomNav::class.java)
                val intent = Intent(requireContext(), BottomNav::class.java)
                startActivity(intent)
                Toast.makeText(requireContext(), "Sign in successful.", Toast.LENGTH_LONG).show()
                //finish
               // exitProcess(0)
            }
        }
    }
    // Kotlin object to access user credentials anywhere in the app.
    object SavedPreference {
        const val EMAIL= "email"
        const val USERNAME="username"

        private  fun getSharedPreference(ctx: Context?): SharedPreferences? {
            return PreferenceManager.getDefaultSharedPreferences(ctx)
        }
        private fun  editor(context: Context, const:String, string: String){
            getSharedPreference(
                context
            )?.edit()?.putString(const,string)?.apply()
        }
        fun getEmail(context: Context)= getSharedPreference(
            context
        )?.getString(EMAIL,"")

        fun setEmail(context: Context, email: String){
            editor(
                context,
                EMAIL,
                email
            )
        }

        fun setUsername(context: Context, username:String){
            editor(
                context,
                USERNAME,
                username
            )
        }

        fun getUsername(context: Context) = getSharedPreference(
            context
        )?.getString(USERNAME,"")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = ProgressDialog(requireContext())
        val configuration : PayPalConfiguration = PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)

        txt_to_login.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        btn_signup.setOnClickListener {
            createUser()
        }
        google_signin.setOnClickListener {
            googleSignIn()
        }
    }

    private fun createUser() {
        val userEmail: String = signup_email.text!!.toString()
        val userPwd: String = signup_pwd.text!!.toString()
        val confirmPwd: String = signup_cpwd.text!!.toString()

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(requireContext(), "Please enter your Email", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(userPwd)) {
            Toast.makeText(requireContext(), "Please enter your Password", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(confirmPwd)) {
            Toast.makeText(requireContext(), "Please confirm your password", Toast.LENGTH_SHORT).show()
        } else if (userPwd!!.length < 8) {
            Toast.makeText(requireContext(), "Password too short. 8 characters minimum allowed", Toast.LENGTH_LONG).show()
        } else if (userPwd != confirmPwd) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            dialog.setTitle("One minute...")
            dialog.show()
            firebaseAuth!!.createUserWithEmailAndPassword(userEmail, userPwd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dialog.dismiss()
                        Toast.makeText(requireContext(), "All set up! :)", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_signUpFragment_to_bottomNav)
                    } else {
                        dialog.dismiss()
                        Toast.makeText(
                            requireContext(),
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        task.exception!!.message?.let {
                            Log.d(TAG, it)
                        }
                    }
                }

        }
    }
}