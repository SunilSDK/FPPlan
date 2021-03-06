package com.rtc.feedprodution.ui.orderPendingDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.response.orderDispatchDetails.ViewOrderDispatchDone
import com.rtc.feedprodution.model.response.orderPendingDetails.ViewOrderNotDone
import java.math.MathContext
import javax.inject.Inject

class OrderPendingDetailsAdapter(private val listener: OrderDetailsItemListener) :

    RecyclerView.Adapter<OrderViewHolder>() {
    private var lastPosition = -1

       private lateinit var context :Context
    interface OrderDetailsItemListener {
        fun onClickedDetails(viewOrderNotDone: ViewOrderNotDone)
        fun onClickedRemark(viewOrderNotDone: ViewOrderNotDone)
    }

    private val items = ArrayList<ViewOrderNotDone>()
    private lateinit var item: ViewOrderNotDone

    fun setItems(items: ArrayList<ViewOrderNotDone>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_pending, parent, false)

        context= parent.getContext();
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {


        val stringBuilder1=StringBuilder()
        stringBuilder1.append("<strong>Order No. : </strong>").append(items[position].oRDERNO+"  ")

       val stringBuilder=StringBuilder()
        stringBuilder.append("<strong>Order QTY : </strong>").append(items[position].orderQTY.toString()+"  ")
        stringBuilder.append("<br><br><strong>Order Date : </strong>").append(items[position].oRDERDATETIME)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textDescription.text=Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY);
            holder.textTitle.text = Html.fromHtml(stringBuilder1.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            holder.textDescription.text=Html.fromHtml(stringBuilder.toString());
            holder.textTitle.text=Html.fromHtml(stringBuilder1.toString());
        }
        item = items[position]
        holder.itemLayout.tag=item
        holder.BTNViewDetails.setOnClickListener {  listener.onClickedDetails(items[position])}
        holder.BTNremark.setOnClickListener {  listener.onClickedRemark(items[position])}


        setAnimation(holder.itemView,position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}

class OrderViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val itemLayout: LinearLayoutCompat = itemView.findViewById(R.id.home_layout)
    val textTitle: TextView = itemView.findViewById(R.id.text_title)
    val textDescription: TextView = itemView.findViewById(R.id.text_description)
    val BTNViewDetails: TextView = itemView.findViewById(R.id.BTNViewDetails)
    val BTNremark: TextView = itemView.findViewById(R.id.BTNremark)


}