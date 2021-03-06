package com.rtc.feedprodution.ui.orderPendingDetails.dailog;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.response.orderPendingDetails.TORDERDEAILS
import com.rtc.feedprodution.ui.orderDispatchDetails.OrderPendingSubDetailsAdapter

class OrderPendingDialog : DialogFragment(),
    OrderPendingSubDetailsAdapter.OrderPendingDetailItemListener {

        private lateinit var adapterSub: OrderPendingSubDetailsAdapter
        private lateinit var recyclerview : RecyclerView
        private lateinit var closeIV : ImageView

        companion object {
        const val TAG = "OrderDialog"

        private lateinit var items: List<TORDERDEAILS>
        fun newInstance(items: List<TORDERDEAILS>): OrderPendingDialog {
/*        val args = Bundle()
        args.putString(KEY_TITLE, title)
        args.putString(KEY_SUBTITLE, subTitle)*/
                Companion.items =items
        val fragment = OrderPendingDialog()
//        fragment.arguments = args
        return fragment
        }

        }

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_pending_orders_dialog, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)


                closeIV.setOnClickListener {
                        dismiss()
                }
         setupRecyclerSubView()

                adapterSub.setItems(items)
        }

        private fun setupRecyclerSubView() {
                adapterSub = OrderPendingSubDetailsAdapter(this)

                recyclerview.layoutManager = LinearLayoutManager(activity)
                recyclerview.scheduleLayoutAnimation()
                recyclerview.adapter = adapterSub
        }


        override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
        )
        }

private fun setupView(view: View) {

    recyclerview=view.findViewById(R.id.recyclerview)
    closeIV=view.findViewById(R.id.closeIV)
        }

        override fun onClickedSubDash(view: TORDERDEAILS) {
            Log.d("Test",view.OrderQTY)
        }
}