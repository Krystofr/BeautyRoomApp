package app.christopher.beautyroomapp.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import app.christopher.beautyroomapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_feedback.*

class Feedback : AppCompatActivity() {
    private val feedbackDBInstance = Firebase.firestore
    private val firebaseAuth: FirebaseAuth? = null
    private lateinit var feedbackText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        title = "Feedback"

        feedback_submit_button.setOnClickListener {
            submitUserFeedback()
        }
    }

    private fun submitUserFeedback() {
        feedbackText = feed_text!!.text.toString()
        if (!overall_service.isChecked && !customer_support.isChecked
                && !speed.isChecked && !service_quality.isChecked
                && !transparency.isChecked
        ) {
            val snack = Snackbar.make(c_layout, "Please check at least one feedback category above", Snackbar.LENGTH_LONG).setAction("Action", null)
            snack.setTextColor(resources.getColor(R.color.black))
            val snackbar = snack.view
            snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
            snack.show()
        }
        else if (feedbackText.isEmpty()) {
            val snack = Snackbar.make(c_layout, "Please enter your feedback message", Snackbar.LENGTH_LONG).setAction("Action", null)
            snack.setTextColor(resources.getColor(R.color.black))
            val snackbar = snack.view
            snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
            snack.show()
        } else{
            successDialog()
        }
    }
    private fun successDialog(){
      val alertDialog = AlertDialog.Builder(this)
          alertDialog.setCancelable(false)
          alertDialog.setTitle("Confirmation")
          alertDialog.setMessage("Submit this feedback?")
          alertDialog.setPositiveButton("YES"){ _, _, ->
              val userFeedback = hashMapOf<String, Any>(
                  "Message" to feedbackText,
              )
              feedbackDBInstance
                      .collection("Feedback")
                      .document("Message")
                      .set(userFeedback)
                      .addOnCompleteListener { task ->
                          if (task.isSuccessful) {
                              Toast.makeText(this, "Thanks for your feedback. It helps us improve your experience with us!", Toast.LENGTH_LONG).show()
                              finish()
                          } else {
                              Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG).show()
                              task.exception!!.message?.let {
                                  // Log.d(TAG, it)
                              }
                          } //Firestore Security Rules - firebaseAuth!!.currentUser?.email.toString()
                      }
          }
          alertDialog.setNegativeButton("CANCEL"){ action, _, ->
              action.cancel()
          }
          .show()
    }
}