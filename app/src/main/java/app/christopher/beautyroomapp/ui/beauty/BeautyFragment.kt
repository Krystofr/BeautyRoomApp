package app.christopher.beautyroomapp.ui.beauty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.christopher.beautyroomapp.R

class BeautyFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_beauty, container, false)

       // slider_viewPager

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Future Update!")
                .setMessage("To be implemented...")
                .setNeutralButton("GOT IT"){ _, _, ->
                    findNavController().navigate(R.id.action_navigation_beauty_to_navigation_dashboard)
                }
    }
}