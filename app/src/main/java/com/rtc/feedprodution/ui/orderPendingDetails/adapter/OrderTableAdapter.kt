package com.rtc.feedprodution.ui.orderPendingDetails

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
import com.rtc.feedprodution.model.response.orderPendingDetails.ViewOrderNotDone
import java.math.MathContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class OrderTableAdapter(private val listener: OrderTableListener) :

    RecyclerView.Adapter<OrderTableViewHolder>(), Filterable {
    private var lastPosition = -1
    var itemsFilterList = ArrayList<ViewOrderNotDone>()

       private lateinit var context :Context
    interface OrderTableListener {
        fun onClickedDetails(viewOrderNotDone: ViewOrderNotDone)
        fun onClickedRemark(viewOrderNotDone: ViewOrderNotDone)
    }

    private val items = ArrayList<ViewOrderNotDone>()
    private lateinit var item: ViewOrderNotDone

    fun setItems(items: ArrayList<ViewOrderNotDone>) {
        this.items.clear()
        this.items.addAll(items)
        itemsFilterList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderTableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_table_pending, parent, false)

        context= parent.getContext();
        return OrderTableViewHolder(view)
    }

    override fun getItemCount(): Int = itemsFilterList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderTableViewHolder, position: Int) {

        item = itemsFilterList[position]
/*        val stringBuilder1=StringBuilder()
        stringBuilder1.append("<strong>Order No. : </strong>").append(items[position].oRDERNO+"  ")

       val stringBuilder=StringBuilder()
        stringBuilder.append("<strong>Order QTY : </strong>").append(items[position].orderQTY.toString()+"  ")
        stringBuilder.append("<br><br><strong>Order Date : </strong>").append(items[position].oRDERDATETIME)*/

        holder.tvSrNo.text=((position+1).toString())
        holder.tvOrderNo.text=item.oRDERNO
        holder.tvPartyName.text=item.cUSTOMERNAME
        holder.tvDateTime.text=item.oRDERDATETIME.replace("T"," ")
        holder.tvTimeDiff.text=(item.timeDiff).toString()
        if(item.timeDiff>=24){
            holder.itemLayout.setBackgroundResource(R.color.red_200)
        }else{
            holder.itemLayout.setBackgroundResource(R.color.green_200)
        }


        holder.tvOrderNo.setOnClickListener {
            listener.onClickedDetails(itemsFilterList[position])
        }
        holder.btnRemark.setOnClickListener {
            listener.onClickedRemark(itemsFilterList[position])
        }


        holder.itemLayout.tag=item

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
                    val resultList = ArrayList<ViewOrderNotDone>()
                    for (row in items) {
                        if (row.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
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
                itemsFilterList = results?.values as ArrayList<ViewOrderNotDone>
                notifyDataSetChanged()
            }

        }
    }

}

class OrderTableViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val itemLayout: LinearLayoutCompat = itemView.findViewById(R.id.home_layout)
    val tvSrNo: TextView = itemView.findViewById(R.id.tvSrNo)
    val tvOrderNo: TextView = itemView.findViewById(R.id.tvOrderNo)
    val tvPartyName: TextView = itemView.findViewById(R.id.tvPartyName)
    val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)
    val tvTimeDiff: TextView = itemView.findViewById(R.id.tvTimeDiff)
    val btnRemark: TextView = itemView.findViewById(R.id.btnRemark)

}