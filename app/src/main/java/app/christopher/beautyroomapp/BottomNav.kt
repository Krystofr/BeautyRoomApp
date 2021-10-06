package app.christopher.beautyroomapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import app.christopher.beautyroomapp.toolbar.AboutUs
import app.christopher.beautyroomapp.toolbar.Contact
import app.christopher.beautyroomapp.toolbar.Feedback
import app.christopher.beautyroomapp.toolbar.Testimonials
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class BottomNav : AppCompatActivity() {
    private val nav: NavController? = null
    // declare the GoogleSignInClient
    lateinit var mGoogleSignInClient: GoogleSignInClient
    // val auth is initialized by lazy
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)
/*
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        */
        //supportActionBar?.hide()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this, gso)

        val actionBar = supportActionBar
        actionBar!!.elevation = 6.0F
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_dashboard,
                        R.id.navigation_beauty,
                        R.id.navigation_bookings,
                        R.id.navigation_more
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_about -> {
                val intent = Intent(this, AboutUs::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }
            R.id.action_contact -> {
                val intent = Intent(this, Contact::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }
            R.id.action_feedback -> {
                val intent = Intent(this, Feedback::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_testimonials -> {
                val intent = Intent(this, Testimonials::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }
            R.id.action_logout -> {
                logoutDialog()
               return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logoutDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Are you sure you want to logout?")
        dialog.setCancelable(false)
        dialog.setPositiveButton("YES"){ dialogInterface, which ->
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener {
                        this.finish()
                        moveTaskToBack(true)
                        finishAndRemoveTask()
                        Toast.makeText(this, "logging out...", Toast.LENGTH_LONG).show()
                    }
        }
        dialog.setNegativeButton("CANCEL"){ dialog_, which_ ->
            dialog_.cancel()
        }
        dialog.show()
    }
}