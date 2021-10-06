package app.christopher.beautyroomapp.ui.dashboard.external

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import app.christopher.beautyroomapp.R
import app.christopher.beautyroomapp.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_services.*

class ServicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        title = "SELECT A SERVICE"

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "CUT & STYLE"
                }
                1 -> {
                    tab.text = "TREATMENT & TEXTURE"
                }
                2 -> {
                    tab.text = "FOR MEN"
                }
                3 -> {
                    tab.text = "FOR MEN"
                }
            }
        }.attach()

        bh.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("BUSINESS HOURS")
        val dialogView = layoutInflater.inflate(R.layout.bh_dialog_popup, null)
        dialog.setView(dialogView)
        dialog.create()
        dialog.show()
    }
}