package app.christopher.beautyroomapp.toolbar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.christopher.beautyroomapp.R

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        title = "About Us"
    }
    fun phoneCall(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel: " + "07883428612")
        startActivity(intent)
    }

    fun openMaps(view: View) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        val locationIntentUri = Uri.parse("https://www.google.co.uk/maps/place/The+Xpression+Hair+%26+Beauty/@52.9549514,-1.1544238,17z/data=!3m1!4b1!4m5!3m4!1s0x4879c180847c1dd3:0x4878a8779851490d!8m2!3d52.9549608!4d-1.152236?hl=en")
        val mapsIntent = Intent(Intent.ACTION_VIEW, locationIntentUri)
        // Make the Intent explicit by setting the Google Maps package
        mapsIntent.setPackage("com.google.android.apps.maps")
        mapsIntent.resolveActivity(packageManager)?.let {
            startActivity(mapsIntent)
        }
    }

    fun sendEmail(view: View) {
        val sendItent = Intent(Intent.ACTION_SEND)
        sendItent.type = "text/plain"
        sendItent.putExtra(Intent.EXTRA_SUBJECT, "Xpression Enquiries")
        sendItent.putExtra(Intent.EXTRA_TEXT, "Email Body")
        startActivity(sendItent)
    }
}