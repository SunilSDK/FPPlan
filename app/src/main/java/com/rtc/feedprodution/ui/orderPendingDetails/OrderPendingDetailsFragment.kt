package com.rtc.feedprodution.ui.orderPendingDetails

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.orderPendingDetails.ResOrderPendingDetails
import com.rtc.feedprodution.model.response.orderPendingDetails.ViewOrderNotDone
import com.rtc.feedprodution.ui.orderPendingDetails.dailog.OrderPendingDialog
import com.rtc.feedprodution.ui.orderPendingDetails.dailog.OrderRemarkDialog
import com.rtc.feedprodution.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OrderPendingDetailsFragment : Fragment(),OrderPendingDetailsAdapter.OrderDetailsItemListener,
        OrderTableAdapter.OrderTableListener {

    private val orderPendingDetailsViewModel: OrderPendingDetailsViewModel by viewModels()
    private lateinit var adapter: OrderPendingDetailsAdapter
    private lateinit var orderTableAdapter: OrderTableAdapter

    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var recyclerview : RecyclerView
    private lateinit var scrollableHS: HorizontalScrollView
    private var isShow:Boolean = false

    private lateinit var order_search: SearchView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_pending_order_details, container, false)


        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        recyclerview= root.findViewById(R.id.recyclerview)
        scrollableHS= root.findViewById(R.id.scrollableHS)
        order_search= root.findViewById(R.id.order_search)

        recyclerview.setNestedScrollingEnabled(false)

        setupRecyclerView()
        subscribeObservers()
        val userFCMToken= UserFCMToken(orderPendingDetailsViewModel.getSp().getUserID(), "")
        orderPendingDetailsViewModel.setPendingOrder(
            MainStateEvent.GetMasterEvent,
            orderPendingDetailsViewModel.getSp().getToken(),
            userFCMToken
        )

        swipeRefreshLayout.setOnRefreshListener {

            isShow=false
            val userFCMToken= UserFCMToken(orderPendingDetailsViewModel.getSp().getUserID(), "")
            subscribeObservers()
            orderPendingDetailsViewModel.setPendingOrder(
                MainStateEvent.GetMasterEvent,
                orderPendingDetailsViewModel.getSp().getToken(),
                userFCMToken
            )
        }

        return root
    }

    private fun subscribeObservers(){
        activity?.let {
            orderPendingDetailsViewModel.dataStateMaster.observe(it, Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ResOrderPendingDetails> -> {
                        displayLoading(false)
                        swipeRefreshLayout.setRefreshing(false);
                        scrollableHS.visibility = View.VISIBLE
                        populateRecyclerViewTable(dataState.data.viewOrderNotDone)
                    }
                    is DataState.Loading -> {
                        displayLoading(true)
                        scrollableHS.visibility = View.GONE
                    }
                    is DataState.Error -> {
                        displayLoading(false)
                        scrollableHS.visibility = View.GONE
                        displayError(dataState.exception.message)
                    }
                }
            })
        }
    }

    private fun subscribeSubObservers(_orderID: String,type:String){

        activity?.let {
            orderPendingDetailsViewModel.dataStateMaster.observe(it, Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ResOrderPendingDetails> -> {
                        displayLoading(false)
                        Log.d("Sunil", _orderID.toString())

                        if (isShow) {
                            if (type.equals("Remark")) {

                                OrderRemarkDialog.newInstance(dataState.data.tORDERDEAILS.
                                filter {
                                    it.oRDERNO.equals(_orderID)
                                })
                                    .show(
                                        requireActivity().supportFragmentManager.beginTransaction(),
                                        OrderPendingDialog.TAG
                                    )

                            } else {
                                OrderPendingDialog.newInstance(dataState.data.tORDERDEAILS.filter {
                                    it.oRDERNO.equals(_orderID)
                                })
                                    .show(
                                        requireActivity().supportFragmentManager.beginTransaction(),
                                        OrderPendingDialog.TAG
                                    )
                            }
                        }
                    }
                    is DataState.Loading -> {
                        displayLoading(true)
                    }
                    is DataState.Error -> {
                        displayLoading(false)
                        displayError(dataState.exception.message)
                    }
                }
            })
        }
    }


    private fun displayError(message: String?){
        if(message != null){
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(activity, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean){
        swipeRefreshLayout.isRefreshing = isLoading

    }

    private fun populateRecyclerView(viewOrderNotDone: List<ViewOrderNotDone>){
        if (!viewOrderNotDone.isNullOrEmpty()) adapter.setItems(ArrayList(viewOrderNotDone))
    }

    private fun populateRecyclerViewTable(viewOrderNotDone: List<ViewOrderNotDone>){
        if (!viewOrderNotDone.isNullOrEmpty()) orderTableAdapter.setItems(ArrayList(viewOrderNotDone))
    }



    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupRecyclerView() {
        adapter = OrderPendingDetailsAdapter(this)
        orderTableAdapter = OrderTableAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.scheduleLayoutAnimation()
        (recyclerview.layoutManager as LinearLayoutManager).setAutoMeasureEnabled(true)


        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val firstPos: Int = (recyclerview.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if (firstPos > 0) {
                    swipeRefreshLayout.setEnabled(false)
                } else {
                    swipeRefreshLayout.setEnabled(true)
                }
            }
        })

        recyclerview.adapter = orderTableAdapter

        order_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                orderTableAdapter.filter.filter(newText)
                Log.d("Sunil",newText.toString())
                return false
            }

        })

    }

    override fun onClickedDetails(viewOrderNotDone: ViewOrderNotDone) {
        isShow=true
        subscribeSubObservers(viewOrderNotDone.oRDERNO,"Details")
    }

    override fun onClickedRemark(viewOrderNotDone: ViewOrderNotDone) {
        isShow=true
        subscribeSubObservers(viewOrderNotDone.oRDERNO,"Remark")
    }


}