package app.christopher.beautyroomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.christopher.beautyroomapp.R
import app.christopher.beautyroomapp.model.Specialists

class SpecialistsAdapter(val items : ArrayList<Specialists>) : RecyclerView.Adapter<SpecialistsAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.specialist_item_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val specialists: Specialists = items[position]
        holder.bind(specialists)

    }
    override fun getItemCount() = items.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null
        var name: TextView? = null
        var phone: TextView? = null

        init {
            image = itemView.findViewById(R.id.holder_img)
            name = itemView.findViewById(R.id.txt_name)
            phone = itemView.findViewById(R.id.txt_phone)
        }

        fun bind(specialist_item: Specialists) {
            name?.text = specialist_item.getName().toString()
            phone?.text = specialist_item.getPhone().toString()
            image?.setImageResource(specialist_item.getImage())
        }
    }
}
/*
   override fun onBindViewHolder(holder: MyServiceViewHolder, position: Int) {
        holder.txtName!!.text = StringBuilder().append(list[position].name)
        holder.txtPrice!!.text = StringBuilder("£").append(list[position].price)
    }
 */