@file:Suppress("Annotator")

package com.rtc.feedprodution.data.networkdata
import com.rtc.feedprodution.model.request.UserFCMToken
import com.rtc.feedprodution.model.response.masters.ResMasters
import com.rtc.feedprodution.model.response.orderDispatchDetails.ResOrderDispatchDetails
import com.rtc.feedprodution.model.response.orderPendingDetails.ResOrderPendingDetails
import com.rtc.feedprodution.model.response.vehiclewiseq.ResVehicleWiseQ
import com.rtc.feedprodution.model.userDetails.ResUserDetails
import retrofit2.http.*

interface FeedProRetrofit {

    @FormUrlEncoded
    @POST("v1/login")
    suspend fun getLoginData(
        @Field("userName") userName: String?,
        @Field("password") password: String?,
        @Field("grant_type") grant_type: String?
    ): ResUserDetails


    @POST("getAccessSetting")
    suspend fun getAllMasters(
        @Header("Authorization") auth: String?,
        @Body userFCMToken: UserFCMToken?
    ): ResMasters

    @POST("getDispatchOrder")
    suspend fun getDispatchOrder(
            @Header("Authorization") auth: String?,
            @Body userFCMToken: UserFCMToken?
    ): ResOrderDispatchDetails


    @POST("getPendingOrder")
    suspend fun getPendingOrder(
            @Header("Authorization") auth: String?,
            @Body userFCMToken: UserFCMToken?
    ): ResOrderPendingDetails


    @POST("getVehicleWiseQ")
    suspend fun getVehiclesQ(
            @Header("Authorization") auth: String?,
            @Body userFCMToken: UserFCMToken?
    ): ResVehicleWiseQ


}