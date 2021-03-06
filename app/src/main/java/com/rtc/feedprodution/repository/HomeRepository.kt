package com.rtc.feedprodution.repository

import android.util.Log
import com.rtc.feedprodution.data.networkdata.FeedProRetrofit
import com.rtc.feedprodution.data.room.FeedDatabase
import com.rtc.feedprodution.model.HomeScreen
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class HomeRepository
constructor(
    private val feedDatabase: FeedDatabase,
    private val feedProRetrofit: FeedProRetrofit
){
        suspend fun getMenus(): kotlinx.coroutines.flow.Flow<DataState<List<HomeScreen>>> =
            flow{
            emit(DataState.Loading)
            delay(1000)
                try{
                    val listHomeScreen: MutableList<HomeScreen> = mutableListOf()        // or arrayListOf
                    listHomeScreen.add(HomeScreen("Pending Order",1,1,1))
                    listHomeScreen.add(HomeScreen("Vehicles in Queue",1,1,1))
                    listHomeScreen.add(HomeScreen("Orders",1,1,1))
                    listHomeScreen.add(HomeScreen("Stock",1,1,1))
                    emit(DataState.Success(listHomeScreen))
                }catch (e: Exception){
                    emit(DataState.Error(e))
                }

           }
}
