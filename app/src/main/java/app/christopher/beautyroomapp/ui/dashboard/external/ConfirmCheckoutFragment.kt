package app.christopher.beautyroomapp.ui.dashboard.external

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.fragment_confirm_checkout.*

class ConfirmCheckoutFragment : Fragment(R.layout.fragment_confirm_checkout){

    private lateinit var navController : NavController
    private val args: ConfirmCheckoutFragmentArgs by navArgs()
    private lateinit var dialog : ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        dialog = ProgressDialog(requireContext())

        receiver_txt_specialist.text = args.specialistNameRecipient
        receiver_txt_service.text = args.serviceNameRecipient
        receiver_txt_time.text = args.timeRecipient
        receiver_txt_period.text = args.periodRecipient
        receiver_txt_price.text = args.priceRecipient


        cv_proceed.setOnClickListener {
            val serviceName = receiver_txt_service.text.toString()
            val serviceTime = receiver_txt_time.text.toString()
            val duration = receiver_txt_period.text.toString()
            val servicePrice = receiver_txt_price.text.toString()
            dialog.show()
            val action = ConfirmCheckoutFragmentDirections.actionConfirmCheckoutFragmentToPaymentFragment(
                    serviceName, serviceTime, duration, servicePrice
            )
            dialog.dismiss()
            findNavController().navigate(action)
        }
    }

}

/*
lateinit var specialistRecipient: String
lateinit var serviceRecipient: String
lateinit var timeRecipient: String
lateinit var periodRecipient: String
lateinit var priceRecipient: String
*/

/*
specialistRecipient = arguments?.getString("specialist_name_recipient").toString()
serviceRecipient = arguments?.getString("service_name_recipient").toString()
timeRecipient = arguments?.getString("time_recipient").toString()
periodRecipient = arguments?.getString("period_recipient").toString()
priceRecipient = arguments?.getString("price_recipient").toString()
*/
/*
val specialistName = "The $specialistRecipient"
receiver_txt_specialist.text = specialistName

val serviceName = "$serviceRecipient"
receiver_txt_service.text = serviceName

val time = "$timeRecipient"
receiver_txt_time.text = time

val period = "$periodRecipient"
receiver_txt_period.text = period

val price = "$priceRecipient"
receiver_txt_price.text = price

navController.navigate(R.id.action_confirmCheckoutFragment_to_kristianaFragment)
*/
