package com.rtc.feedprodution.model.response.vehiclewiseq


import com.google.gson.annotations.SerializedName

data class ResVehicleWiseQ(
    @SerializedName("T_ORDER_DEAILS")
    var tORDERDEAILS: List<TORDERDEAILS>,
    @SerializedName("View_VehicleWiseQ")
    var viewVehicleWiseQ: List<ViewVehicleWiseQ>


) {
    override fun toString(): String {
        return "ResVehicleWiseQ(tORDERDEAILS=$tORDERDEAILS, viewVehicleWiseQ=$viewVehicleWiseQ)"
    }
}