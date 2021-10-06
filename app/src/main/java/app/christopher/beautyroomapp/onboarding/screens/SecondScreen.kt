package app.christopher.beautyroomapp.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_second_screen.view.*

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_screen, container, false)

        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.next2.setOnClickListener {
            viewPager2?.currentItem = 2
        }
        return view    }

}