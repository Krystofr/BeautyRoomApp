package app.christopher.beautyroomapp.ui.dashboard.external.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.christopher.beautyroomapp.R
import kotlinx.android.synthetic.main.activity_payment_details.*
import org.json.JSONException
import org.json.JSONObject

class PaymentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        title = "Success"
        val intent = intent
        try {
            val jsonObject = JSONObject(intent.getStringExtra("PaymentDetails"))
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"))
        } catch (e: JSONException) {
            e.printStackTrace()
            e.message
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showDetails(response: JSONObject, paymentAmount: String?) {
        try {
            txt_Id.text = response.getString("id")
            txt_status.text = response.getString("state")
            txt_amount.text = "£$paymentAmount"
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }
    }
}