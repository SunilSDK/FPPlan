package com.rtc.feedprodution.model.response.vehiclewiseq


import com.google.gson.annotations.SerializedName

data class ViewVehicleWiseQ(
    @SerializedName("ORDER_DATE_TIME")
    var oRDERDATETIME: String, // 2021-01-15T09:53:30
    @SerializedName("Order_QTY")
    var orderQTY: Double, // 30.0
    @SerializedName("SALE_INVOICE_DATE")
    var sALEINVOICEDATE: String, // 2021-01-15T00:00:00
    @SerializedName("SALE_INVOICE_NO")
    var sALEINVOICENO: String, // 0091653196
    @SerializedName("Sales_QTY")
    var salesQTY: Double, // 20.0
    @SerializedName("VEHICLE_NO")
    var vEHICLENO: String // MH42AF0246


) {
    override fun toString(): String {
        return "ViewVehicleWiseQ(oRDERDATETIME='$oRDERDATETIME', orderQTY=$orderQTY, sALEINVOICEDATE='$sALEINVOICEDATE', sALEINVOICENO='$sALEINVOICENO', salesQTY=$salesQTY, vEHICLENO='$vEHICLENO')"
    }
}