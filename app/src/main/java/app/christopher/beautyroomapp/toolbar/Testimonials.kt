package app.christopher.beautyroomapp.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.christopher.beautyroomapp.R

class Testimonials : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testimonials)
        title = "Testimonials"
    }
}