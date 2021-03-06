package com.rtc.feedprodution.ui.orderDispatchDetails

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.response.orderDispatchDetails.ViewOrderInvoiceDetail

class OrderDispatchSubDetailsAdapter(private val listener: OrderInvoiceDetailItemListener) :
    RecyclerView.Adapter<OrderViewHolder1>() {
    private var lastPosition = -1
    private lateinit var context : Context
    interface OrderInvoiceDetailItemListener {
        fun onClickedSubDash(view: ViewOrderInvoiceDetail)
    }

    private val items = ArrayList<ViewOrderInvoiceDetail>()
    private lateinit var item: ViewOrderInvoiceDetail

    fun setItems(items: List<ViewOrderInvoiceDetail>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_dispatch,parent,false)
        context= parent.getContext();
        return OrderViewHolder1(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: OrderViewHolder1, position: Int) {
        val stringBuilder1=StringBuilder()
        stringBuilder1.append("<strong>Order No. : </strong>").append(items[position].oRDERNO+"  ")
        stringBuilder1.append("<strong>Invoice No : </strong>").append(items[position].sALEINVOICENO)


        val stringBuilder=StringBuilder()
        stringBuilder.append("<strong>Order QTY : </strong>").append(items[position].qTY.toString()+"  ")
        stringBuilder.append("<strong>Sale QTY : </strong>").append(items[position].sALESQTY)
        stringBuilder.append("<br><br><strong>Order Date : </strong>").append(items[position].oRDERDATETIME )
        stringBuilder.append("<br><br><strong>Sales Date : </strong>").append(items[position].sALEINVOICEDATE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textDescription.text= Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY);
            holder.textTitle.text = Html.fromHtml(stringBuilder1.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            holder.textDescription.text= Html.fromHtml(stringBuilder.toString());
            holder.textTitle.text= Html.fromHtml(stringBuilder1.toString());
        }
        item = items[position]

        holder.itemLayout.setOnClickListener {
            listener.onClickedSubDash(items[position])
        }

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

class OrderViewHolder1(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val itemLayout: LinearLayoutCompat = itemView.findViewById(R.id.home_layout)
    val textTitle: TextView = itemView.findViewById(R.id.text_title)
    val textDescription: TextView = itemView.findViewById(R.id.text_description)

}