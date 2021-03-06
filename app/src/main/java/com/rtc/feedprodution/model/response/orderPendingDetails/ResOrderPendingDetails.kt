package com.rtc.feedprodution.model.response.orderPendingDetails


import com.google.gson.annotations.SerializedName

data class ResOrderPendingDetails(
    @SerializedName("T_ORDER_DEAILS")
    var tORDERDEAILS: List<TORDERDEAILS>,
    @SerializedName("View_Order_Not_Done")
    var viewOrderNotDone: List<ViewOrderNotDone>


) {
    override fun toString(): String {
        return "ResOrderPendingDetails(tORDERDEAILS=$tORDERDEAILS, viewOrderNotDone=$viewOrderNotDone)"
    }
}