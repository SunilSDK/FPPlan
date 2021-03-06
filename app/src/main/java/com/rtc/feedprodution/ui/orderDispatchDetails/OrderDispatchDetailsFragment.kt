package com.rtc.feedprodution.ui.orderDispatchDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.orderDispatchDetails.ResOrderDispatchDetails
import com.rtc.feedprodution.model.response.orderDispatchDetails.ViewOrderDispatchDone
import com.rtc.feedprodution.ui.orderDispatchDetails.dialog.OrderDialog
import com.rtc.feedprodution.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.collections.ArrayList


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OrderDispatchDetailsFragment : Fragment(),OrderDispatchDetailsAdapter.OrderDetailsItemListener{

    private val orderDispatchDetailsViewModel: OrderDispatchDetailsViewModel by viewModels()
    private lateinit var progressbar: ProgressBar
    private lateinit var adapter: OrderDispatchDetailsAdapter
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var recyclerview : RecyclerView
    private lateinit var order_search: SearchView

    private var isShow:Boolean = false
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_order_dispatch_details, container, false)

        progressbar = root.findViewById(R.id.progress_bar)
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        recyclerview= root.findViewById(R.id.blog_recyclerview)
        order_search= root.findViewById(R.id.order_search)

        setupRecyclerView()
        subscribeObservers()
        val userFCMToken= UserFCMToken(orderDispatchDetailsViewModel.getSp().getUserID(), "")
        orderDispatchDetailsViewModel.setDispatchOrder(MainStateEvent.GetMasterEvent,
                orderDispatchDetailsViewModel.getSp().getToken(),
                userFCMToken)

        swipeRefreshLayout.setOnRefreshListener {

            isShow=false
            val userFCMToken= UserFCMToken(orderDispatchDetailsViewModel.getSp().getUserID(), "")
            subscribeObservers()
            orderDispatchDetailsViewModel.setDispatchOrder(
                    MainStateEvent.GetMasterEvent,
                    orderDispatchDetailsViewModel.getSp().getToken(),
                    userFCMToken)
        }

        return root
    }
    private fun subscribeObservers(){
        activity?.let {
            orderDispatchDetailsViewModel.dataStateMaster.observe(it, Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ResOrderDispatchDetails> -> {
                        displayLoading(false)
                        populateRecyclerView(dataState.data.viewOrderDispatchDone)
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

    private fun subscribeSubObservers(_orderID: String){



        activity?.let {
            orderDispatchDetailsViewModel.dataStateMaster.observe(it, Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ResOrderDispatchDetails> -> {
                        displayLoading(false)
                        Log.d("Sunil",_orderID.toString())

                        if(isShow){
                            OrderDialog.newInstance(dataState.data.viewOrderInvoiceDetails.
                            filter {
                                it.oRDERNO.equals(_orderID)
                            })
                                .show(requireActivity().supportFragmentManager.beginTransaction(),
                                    OrderDialog.TAG)
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

    private fun populateRecyclerView(viewOrderDispatchDones: List<ViewOrderDispatchDone>){
        if (!viewOrderDispatchDones.isNullOrEmpty()) adapter.setItems(ArrayList(viewOrderDispatchDones))
    }



    private fun setupRecyclerView() {
        adapter = OrderDispatchDetailsAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.scheduleLayoutAnimation()
        recyclerview.adapter = adapter

        order_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                Log.d("Sunil",newText.toString())
                return false
            }

        })
    }



    override fun onClickedDash(viewOrderDispatchDone: ViewOrderDispatchDone) {
        isShow=true
        subscribeSubObservers(viewOrderDispatchDone.oRDERNO)
    }


}