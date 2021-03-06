package com.rtc.feedprodution.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.response.masters.TSTOCKDETAILS
import com.rtc.feedprodution.ui.login.LoginViewModel
import com.rtc.feedprodution.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DashboardFragment : Fragment(),DashAdapter.DashItemListener {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var progressbar: ProgressBar
    private lateinit var adapter: DashAdapter
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var recyclerview : RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        progressbar = root.findViewById(R.id.progress_bar)
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        recyclerview= root.findViewById(R.id.recyclerview)

        setupRecyclerView()
        subscribeObservers()
        val userFCMToken= UserFCMToken(dashboardViewModel.getSp().getUserID(),"")
        dashboardViewModel.setAccessMasterEvent(MainStateEvent.GetMasterEvent,
            dashboardViewModel.getSp().getToken(),
            userFCMToken)

        swipeRefreshLayout.setOnRefreshListener {
            val userFCMToken= UserFCMToken(loginViewModel.getSp().getUserID(),"")
            subscribeObservers()
            dashboardViewModel.setAccessMasterEvent(
                MainStateEvent.GetMasterEvent,
                dashboardViewModel.getSp().getToken(),
                userFCMToken)
        }

        return root
    }
    private fun subscribeObservers(){
        activity?.let {
            dashboardViewModel.dataStateMaster.observe(it, Observer{ dataState ->
                when(dataState){
                    is DataState.Success<ResMasters> -> {
                        displayLoading(false)
                        populateRecyclerView(dataState.data.tSTOCKDETAILS)
                    }
                    is DataState.Loading ->{
                        displayLoading(true)
                    }
                    is DataState.Error ->{
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

    private fun populateRecyclerView(blogs: List<TSTOCKDETAILS>){
        if (!blogs.isNullOrEmpty()) adapter.setItems(ArrayList(blogs))
    }

    private fun setupRecyclerView() {
        adapter = DashAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
    }


    override fun onClickedDash(title: CharSequence) {
//        TODO("Not yet implemented")
        Toast.makeText(activity, title, Toast.LENGTH_LONG).show()
    }
}