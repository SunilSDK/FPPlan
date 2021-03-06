package com.rtc.feedprodution.ui.home

import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rtc.feedprodution.R
import com.rtc.feedprodution.model.HomeScreen
import com.rtc.feedprodution.ui.login.LoginViewModel
import com.rtc.feedprodution.util.DataState
import com.rtc.feedprodution.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(),HomeScreenAdapter.HomeScreenItemListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var textButton: Button
    private lateinit var progressbar: ProgressBar
    private lateinit var homeScreenAdapter: HomeScreenAdapter
    private lateinit var home_recyclerview: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        progressbar = root.findViewById(R.id.progressbar)
        home_recyclerview= root.findViewById(R.id.home_recyclerview)
        setupRecyclerView()
        subscribeObserversMasters();
        homeViewModel.setStateEvent(MainStateEvent.GetMenusEvent)

        return root
    }

    private fun subscribeObserversMasters(){
        activity?.let {
            homeViewModel.dataState.observe(it, Observer { dataState ->
                when(dataState){
                    is DataState.Success<List<HomeScreen>> -> {
                            Log.d("dataState",dataState.data.toString());
                            populateRecyclerView(dataState.data)
                            homeScreenAdapter.notifyDataSetChanged();
                        }
                    is DataState.Error -> {
                        displayError(dataState.exception.message)
                    }
                    is DataState.Loading -> {
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

    private fun populateRecyclerView(homeScreens: List<HomeScreen>){
        if (!homeScreens.isNullOrEmpty()) homeScreenAdapter.setItems(ArrayList(homeScreens))
    }

    private fun setupRecyclerView() {
        homeScreenAdapter = HomeScreenAdapter(this)
        home_recyclerview.layoutManager = GridLayoutManager(activity,2)
        home_recyclerview.adapter = homeScreenAdapter
        home_recyclerview.itemAnimator
    }


    override fun onClickedHomeScreen(homeTitle: CharSequence) {

         if(homeTitle.toString().equals("Pending Order")){
            homeViewModel.getNav(R.id.orderPendingDetailsFragment)
        }
        else if(homeTitle.toString().equals("Stock Details")){
            homeViewModel.getNav(R.id.dashboardFragment)
        }
        else if(homeTitle.toString().equals("Orders")){
            homeViewModel.getNav(R.id.orderDispatchDetailsFragment)
        }
    }

}