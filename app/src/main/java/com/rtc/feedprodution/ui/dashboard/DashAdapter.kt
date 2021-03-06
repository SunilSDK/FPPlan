package com.rtc.feedprodution.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.response.masters.TSTOCKDETAILS

class DashAdapter(private val listener: DashItemListener) :
    RecyclerView.Adapter<BlogViewHolder>() {

    interface DashItemListener {
        fun onClickedDash(title: CharSequence)
    }

    private val items = ArrayList<TSTOCKDETAILS>()
    private lateinit var item: TSTOCKDETAILS

    fun setItems(items: ArrayList<TSTOCKDETAILS>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details,parent,false)
        return BlogViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.textTitle.text = items[position].pLANTNAME
        holder.textDescription.text = items[position].mATERIALNAME
/*        Glide.with(holder.itemLayout).load(items[position].image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)*/
        item = items[position]

    }

}

class BlogViewHolder(itemView: View, private val listener: DashAdapter.DashItemListener)
    : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: ConstraintLayout = itemView.findViewById(R.id.layout)
    val textTitle: TextView = itemView.findViewById(R.id.text_title)
    val textDescription: TextView = itemView.findViewById(R.id.text_description)
    val image: AppCompatImageView = itemView.findViewById(R.id.image)
    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedDash(textTitle.text)
    }
}