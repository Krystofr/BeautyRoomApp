package app.christopher.beautyroomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.christopher.beautyroomapp.R
import app.christopher.beautyroomapp.model.PaymentsModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

open class FirestorePaymentAdapter(options: FirestoreRecyclerOptions<PaymentsModel>) : FirestoreRecyclerAdapter<PaymentsModel, FirestorePaymentAdapter.PaymentModelHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentModelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_booking_layout, parent, false)
        return PaymentModelHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentModelHolder, position: Int, model: PaymentsModel) {
       holder.service!!.text = model.getServiceName().toString()
        holder.time!!.text = model.getServiceTime().toString()
        holder.duration!!.text = model.getServiceDuration().toString()
        holder.price!!.text = model.getServicePrice().toString()
       // holder.deleteBtn?.setImageResource(model.getDeleteBtn())
    }

    class PaymentModelHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var service: TextView? = null
        var time: TextView? = null
        var duration: TextView? = null
        var price: TextView? = null
        //var deleteBtn: ImageButton? = null

        init {
            service = itemView.findViewById(R.id.item_service)
            time = itemView.findViewById(R.id.item_time)
            duration = itemView.findViewById(R.id.item_duration)
            price = itemView.findViewById(R.id.item_price)
          //  deleteBtn = itemView.findViewById(R.id.delete_btn)
        }
    }
}