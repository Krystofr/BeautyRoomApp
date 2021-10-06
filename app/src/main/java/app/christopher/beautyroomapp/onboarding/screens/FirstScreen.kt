package app.christopher.beautyroomapp.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.next1

class FirstScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.next1.setOnClickListener {
            viewPager2?.currentItem = 1
        }
        return view
    }
}