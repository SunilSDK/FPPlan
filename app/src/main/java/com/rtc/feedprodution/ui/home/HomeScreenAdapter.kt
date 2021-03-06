package com.rtc.feedprodution.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.HomeScreen


class HomeScreenAdapter(private val listener: HomeScreenItemListener) :
    RecyclerView.Adapter<HomeScreenViewHolder>() {
    private val FADE_DURATION = 1000
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }
    interface HomeScreenItemListener {
        fun onClickedHomeScreen(homeTitle: CharSequence)
    }
    private val items = ArrayList<HomeScreen>()
    private lateinit var item: HomeScreen

    fun setItems(items: ArrayList<HomeScreen>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_homescreen,
            parent,
            false
        )
        return HomeScreenViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        holder.menuButton.text = items[position].name
        item = items[position]
        setFadeAnimation(holder.itemView)
    }

}

class HomeScreenViewHolder(
    itemView: View,
    private val listener: HomeScreenAdapter.HomeScreenItemListener
)
    : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val menuButton: Button = itemView.findViewById(R.id.menuButton)

    init {
        menuButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedHomeScreen(menuButton.text)
    }
}