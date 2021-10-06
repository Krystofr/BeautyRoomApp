package app.christopher.beautyroomapp.toolbar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.christopher.beautyroomapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_contact.*

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        title = "Contact Us"
    }
    fun callPhone(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel: " + "07883428612")
        startActivity(intent)
    }

    fun submit(view: View) {
        when {
            name.text!!.toString().isBlank() -> {
                val snack = Snackbar.make(view, "LET'S GET YOUR NAME FIRST", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
            phone.text!!.toString().isBlank() -> {
                val snack = Snackbar.make(view, "ENTER YOUR PHONE NUMBER", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
            phone.length() < 11 -> {
                val snack = Snackbar.make(view, "PHONE NUMBER INVALID", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
            email.text!!.toString().isBlank() -> {
                val snack = Snackbar.make(view, "ENTER YOUR EMAIL PLEASE", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
            subject.text!!.toString().isBlank() -> {
                val snack = Snackbar.make(view, "SET YOUR SUBJECT", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
            message.text!!.toString().isBlank() -> {
                val snack = Snackbar.make(view, "TYPE YOUR MESSAGE", Snackbar.LENGTH_LONG).setAction("Action", null)
                snack.setTextColor(resources.getColor(R.color.black))
                val snackbar = snack.view
                snackbar.setBackgroundColor(resources.getColor(R.color.whitish_gray))
                snack.show()
            }
        }
        if (name.text!!.toString().isNotBlank()
                || phone.text!!.toString().isNotBlank()
                || email.text!!.toString().isNotBlank()
                || subject.text!!.toString().isNotBlank()
                || message.text!!.toString().isNotBlank()){
            Toast.makeText(applicationContext, "Submission successful!", Toast.LENGTH_LONG).show()
        }
    }

    fun openLocation(view: View) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        val locationIntentUri = Uri.parse("https://www.google.co.uk/maps/place/The+Xpression+Hair+%26+Beauty/@52.9549514,-1.1544238,17z/data=!3m1!4b1!4m5!3m4!1s0x4879c180847c1dd3:0x4878a8779851490d!8m2!3d52.9549608!4d-1.152236?hl=en")
        val mapsIntent = Intent(Intent.ACTION_VIEW, locationIntentUri)
        // Make the Intent explicit by setting the Google Maps package
        mapsIntent.setPackage("com.google.android.apps.maps")
        mapsIntent.resolveActivity(packageManager)?.let {
            startActivity(mapsIntent)
        }
    }

    fun openGmail(view: View) {
        val sendItent = Intent(Intent.ACTION_SEND)
        sendItent.type = "text/plain"
        sendItent.putExtra(Intent.EXTRA_SUBJECT, "Xpression Enquiries")
        sendItent.putExtra(Intent.EXTRA_TEXT, "Email Body")
        startActivity(sendItent)
    }
}