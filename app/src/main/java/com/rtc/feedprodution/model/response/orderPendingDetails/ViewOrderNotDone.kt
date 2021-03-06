package com.rtc.feedprodution.model.response.orderPendingDetails


import com.google.gson.annotations.SerializedName

data class ViewOrderNotDone(
    @SerializedName("ORDER_NO")
    var oRDERNO: String, // 0001543614

    @SerializedName("Order_QTY")
    var orderQTY: Double, // 10.0

    @SerializedName("StockQTY")
    var stockQTY: Double, // 10.0
    @SerializedName("inkg")
    var inkg: String, // 10.0
    @SerializedName("CUSTOMER_NAME")
    var cUSTOMERNAME: String, // Sunil

    @SerializedName("TimeDiff")
    var timeDiff: Int, // 870

    @SerializedName("ORDER_DATE_TIME")
    var oRDERDATETIME: String, // 2021-01-15T10:40:12

) {
    override fun toString(): String {
        return "ViewOrderNotDone(oRDERNO='$oRDERNO', orderQTY=$orderQTY, stockQTY=$stockQTY, inkg='$inkg', cUSTOMERNAME='$cUSTOMERNAME', timeDiff=$timeDiff, oRDERDATETIME='$oRDERDATETIME')"
    }
}