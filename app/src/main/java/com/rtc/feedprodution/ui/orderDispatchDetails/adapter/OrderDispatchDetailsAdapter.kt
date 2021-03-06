package com.rtc.feedprodution.ui.orderDispatchDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.response.orderDispatchDetails.ViewOrderDispatchDone
import java.math.MathContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class OrderDispatchDetailsAdapter(private val listener: OrderDetailsItemListener) :

    RecyclerView.Adapter<OrderViewHolder>(), Filterable {
    private var lastPosition = -1
    var itemsFilterList = ArrayList<ViewOrderDispatchDone>()

       private lateinit var context :Context
    interface OrderDetailsItemListener {
        fun onClickedDash(viewOrderDispatchDone: ViewOrderDispatchDone)
    }

    private val items = ArrayList<ViewOrderDispatchDone>()
    private lateinit var item: ViewOrderDispatchDone

    fun setItems(items: ArrayList<ViewOrderDispatchDone>) {
        this.items.clear()
        this.items.addAll(items)
        itemsFilterList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_dispatch, parent, false)

        context= parent.getContext();
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int = itemsFilterList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {


        val stringBuilder1=StringBuilder()
        stringBuilder1.append("<strong>Order No. : </strong>").append(itemsFilterList[position].oRDERNO+"  ")
        stringBuilder1.append("<strong>Invoice No : </strong>").append(itemsFilterList[position].sALEINVOICENO)


       val stringBuilder=StringBuilder()
        stringBuilder.append("<strong>Order QTY : </strong>").append(itemsFilterList[position].orderQTY.toString()+"  ")
        stringBuilder.append("<strong>Sale QTY : </strong>").append(itemsFilterList[position].salesQTY)
        stringBuilder.append("<br><br><strong>Order Date : </strong>").append(itemsFilterList[position].oRDERDATETIME )
        stringBuilder.append("<br><br><strong>Sales Date : </strong>").append(itemsFilterList[position].sALEINVOICEDATE)

//        holder.textDescription.text = stringBuilder.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textDescription.text=Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY);
            holder.textTitle.text = Html.fromHtml(stringBuilder1.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            holder.textDescription.text=Html.fromHtml(stringBuilder.toString());
            holder.textTitle.text=Html.fromHtml(stringBuilder1.toString());
        }
        item = itemsFilterList[position]
        holder.itemLayout.tag=item
            holder.itemLayout.setOnClickListener {
                listener.onClickedDash(itemsFilterList[position])
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Log.d("Sunil",charSearch)

                if (charSearch.isEmpty()) {
                    itemsFilterList = items
                } else {
                    val resultList = ArrayList<ViewOrderDispatchDone>()
                    for (row in items) {
                        if (row.toString().contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    itemsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsFilterList = results?.values as ArrayList<ViewOrderDispatchDone>
                notifyDataSetChanged()
            }

        }
    }
}

class OrderViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val itemLayout: LinearLayoutCompat = itemView.findViewById(R.id.home_layout)
    val textTitle: TextView = itemView.findViewById(R.id.text_title)
    val textDescription: TextView = itemView.findViewById(R.id.text_description)

}