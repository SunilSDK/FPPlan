package com.rtc.feedprodution.model.response.orderDispatchDetails


import com.google.gson.annotations.SerializedName

data class ResOrderDispatchDetails(
    @SerializedName("View_Order_Dispatch_Done")
    var viewOrderDispatchDone: List<ViewOrderDispatchDone>,
    @SerializedName("View_Order_Invoice_Details")
    var viewOrderInvoiceDetails: List<ViewOrderInvoiceDetail>


) {
    override fun toString(): String {
        return "ResOrderDispatchDetails(viewOrderDispatchDone=$viewOrderDispatchDone, viewOrderInvoiceDetails=$viewOrderInvoiceDetails)"
    }
}