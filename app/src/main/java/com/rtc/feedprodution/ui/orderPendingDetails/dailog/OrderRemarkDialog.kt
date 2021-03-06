package com.rtc.feedprodution.ui.orderPendingDetails.dailog;

import android.os.Bundle
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

class OrderRemarkDialog : DialogFragment(),
    OrderPendingSubDetailsAdapter.OrderPendingDetailItemListener {

        private lateinit var adapterSub: OrderPendingSubDetailsAdapter
        private lateinit var recyclerview : RecyclerView
        private lateinit var closeIV : ImageView

        companion object {
        const val TAG = "SimpleDialog"

        private lateinit var items: List<TORDERDEAILS>
        fun newInstance(items: List<TORDERDEAILS>): OrderRemarkDialog {
/*        val args = Bundle()
        args.putString(KEY_TITLE, title)
        args.putString(KEY_SUBTITLE, subTitle)*/
                Companion.items =items
        val fragment = OrderRemarkDialog()
//        fragment.arguments = args
        return fragment
        }

    }

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_remark_dialog, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)



                closeIV.setOnClickListener {
                        dismiss()
                }
        }


        override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
        )
        }

        private fun setupView(view: View) {
            closeIV=view.findViewById(R.id.closeIV)
        }



        override fun onClickedSubDash(view: TORDERDEAILS) {
                TODO("Not yet implemented")
        }
}