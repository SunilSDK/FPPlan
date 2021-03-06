package com.rtc.feedprodution.repository

import android.util.Log
import com.rtc.feedprodution.data.networkdata.FeedProRetrofit
import com.rtc.feedprodution.data.room.FeedDatabase
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import com.rtc.feedprodution.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class LoginRepository
constructor(
    private val feedDatabase: FeedDatabase,
    private val feedProRetrofit: FeedProRetrofit
){
    suspend fun getLoginDetails(userName: String?,
                                password: String?,
                                grant_type: String): kotlinx.coroutines.flow.Flow<DataState<ResUserDetails>> =
        flow {
        emit(DataState.Loading)
        delay(1000)
        try{

            val networkUserDetails = feedProRetrofit.getLoginData(userName,
            password,
            grant_type)

       //     Log.d("Sunil",networkUserDetails.toString())

            feedDatabase.userDetailsDao().clear()
            feedDatabase.userDetailsDao().insert(networkUserDetails)

            val cachedLoginDatas = feedDatabase.userDetailsDao().get()
            emit(DataState.Success(cachedLoginDatas))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getAccessMasters(
                                token: String,o:Any): kotlinx.coroutines.flow.Flow<DataState<ResMasters>> =
        flow {
            emit(DataState.Loading)
            delay(1000)
            try{

                val networkUserDetails = feedProRetrofit.getAllMasters(token,o as UserFCMToken)

            //    Log.d("Sunil",networkUserDetails.toString())

                val cachedAllMasters =networkUserDetails
                emit(DataState.Success(cachedAllMasters))
            }catch (e: Exception){
                emit(DataState.Error(e))
            }
        }


}
