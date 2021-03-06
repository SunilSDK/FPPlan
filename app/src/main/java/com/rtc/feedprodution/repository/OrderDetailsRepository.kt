package com.rtc.feedprodution.repository

import android.util.Log
import com.rtc.feedprodution.data.networkdata.FeedProRetrofit
import com.rtc.feedprodution.data.room.FeedDatabase
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.orderDispatchDetails.ResOrderDispatchDetails
import com.rtc.feedprodution.model.response.orderPendingDetails.ResOrderPendingDetails
import com.rtc.feedprodution.model.response.vehiclewiseq.ResVehicleWiseQ
import com.rtc.feedprodution.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class OrderDetailsRepository
constructor(
    private val feedDatabase: FeedDatabase,
    private val feedProRetrofit: FeedProRetrofit
){

    suspend fun getDispatchOrder(
        token: String,o:Any): kotlinx.coroutines.flow.Flow<DataState<ResOrderDispatchDetails>> =
        flow {
            emit(DataState.Loading)
            delay(1000)
            try{

                val networkUserDetails = feedProRetrofit.getDispatchOrder(token,o as UserFCMToken)

//                                    Log.d("Sunil",networkUserDetails.toString())
                val cachedDispatchOrders =networkUserDetails
                emit(DataState.Success(cachedDispatchOrders))
            }catch (e: Exception){
                emit(DataState.Error(e))
            }
        }

    suspend fun getPengingOrder(
            token: String,o:Any): kotlinx.coroutines.flow.Flow<DataState<ResOrderPendingDetails>> =
            flow {
                emit(DataState.Loading)
                delay(1000)
                try{

                    val networkUserDetails = feedProRetrofit.getPendingOrder(token,o as UserFCMToken)

  //                  Log.d("Sunil",networkUserDetails.toString())
                    val cachedPendingOrders =networkUserDetails
                    emit(DataState.Success(cachedPendingOrders))
                }catch (e: Exception){
                    emit(DataState.Error(e))
                }
            }

    suspend fun getVehiclesQ(
            token: String,o:Any): kotlinx.coroutines.flow.Flow<DataState<ResVehicleWiseQ>> =
            flow {
                emit(DataState.Loading)
                delay(1000)
                try{

                    val networkUserDetails = feedProRetrofit.getVehiclesQ(token,o as UserFCMToken)

              //      Log.d("Sunil",networkUserDetails.toString())
                    val cachedVehiclesQ =networkUserDetails
                    emit(DataState.Success(cachedVehiclesQ))
                }catch (e: Exception){
                    emit(DataState.Error(e))
                }
            }
}
