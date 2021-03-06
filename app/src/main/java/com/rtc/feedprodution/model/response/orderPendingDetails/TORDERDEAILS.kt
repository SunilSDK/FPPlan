package com.rtc.feedprodution.model.response.orderPendingDetails


import com.google.gson.annotations.SerializedName

data class TORDERDEAILS(
    @SerializedName("BILLING_ITEM")
    var bILLINGITEM: String, // 000010
    @SerializedName("CITY")
    var cITY: String, // TAL MOHOL, DIST SOLAPUR
    @SerializedName("CREATED_ON")
    var cREATEDON: String, // 2021-01-15T11:35:33.467
    @SerializedName("CUSTOMER_CODE")
    var cUSTOMERCODE: String, // 501113
    @SerializedName("CUSTOMER_NAME")
    var cUSTOMERNAME: String, // YASHRAJ POULTRY SERVICES
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: String, // 200087
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String, // DESHI FINISHER
    @SerializedName("OPEN_QTY")
    var oPENQTY: Double, // 10.0
    @SerializedName("ORDER_DATE_TIME")
    var oRDERDATETIME: String, // 2021-01-15T10:40:12
    @SerializedName("ORDER_DOC")
    var oRDERDOC: Int, // 10
    @SerializedName("ORDER_NO")
    var oRDERNO: String, // 0001543614
    @SerializedName("ORDER_TIME")
    var oRDERTIME: String, // 10:40:12
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String, // FM02
    @SerializedName("QTY")
    var qTY: Double, // 10.0
    @SerializedName("REGION")
    var rEGION: String, // Maharashtra
    @SerializedName("STATUS")
    var sTATUS: String,
    @SerializedName("UNIT")
    var uNIT: String, // BAG
    @SerializedName("OrderQTY")
    var OrderQTY: String, // BAG
    @SerializedName("StockQTY")
    var StockQTY: String // BAG

) {
    override fun toString(): String {
        return "TORDERDEAILS(bILLINGITEM='$bILLINGITEM', cITY='$cITY', cREATEDON='$cREATEDON', cUSTOMERCODE='$cUSTOMERCODE', cUSTOMERNAME='$cUSTOMERNAME', mATERIALCODE='$mATERIALCODE', mATERIALNAME='$mATERIALNAME', oPENQTY=$oPENQTY, oRDERDATETIME='$oRDERDATETIME', oRDERDOC=$oRDERDOC, oRDERNO='$oRDERNO', oRDERTIME='$oRDERTIME', pLANTCODE='$pLANTCODE', qTY=$qTY, rEGION='$rEGION', sTATUS='$sTATUS', uNIT='$uNIT')"
    }
}