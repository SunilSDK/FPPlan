package com.rtc.feedprodution.model.response.masters


import com.google.gson.annotations.SerializedName

data class TORDERDEAILS(
    @SerializedName("BILLING_ITEM")
    var bILLINGITEM: String,
    @SerializedName("CITY")
    var cITY: String,
    @SerializedName("CREATED_ON")
    var cREATEDON: String,
    @SerializedName("CUSTOMER_CODE")
    var cUSTOMERCODE: String,
    @SerializedName("CUSTOMER_NAME")
    var cUSTOMERNAME: String,
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: String,
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String,
    @SerializedName("OPEN_QTY")
    var oPENQTY: Double,
    @SerializedName("ORDER_DATE_TIME")
    var oRDERDATETIME: String,
    @SerializedName("ORDER_DOC")
    var oRDERDOC: Int,
    @SerializedName("ORDER_NO")
    var oRDERNO: String,
    @SerializedName("ORDER_TIME")
    var oRDERTIME: String,
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String,
    @SerializedName("QTY")
    var qTY: Double,
    @SerializedName("REGION")
    var rEGION: String,
    @SerializedName("STATUS")
    var sTATUS: String,
    @SerializedName("UNIT")
    var uNIT: String


) {
    override fun toString(): String {
        return "TORDERDEAILS(bILLINGITEM='$bILLINGITEM', cITY='$cITY', cREATEDON='$cREATEDON', cUSTOMERCODE='$cUSTOMERCODE', cUSTOMERNAME='$cUSTOMERNAME', mATERIALCODE='$mATERIALCODE', mATERIALNAME='$mATERIALNAME', oPENQTY=$oPENQTY, oRDERDATETIME='$oRDERDATETIME', oRDERDOC=$oRDERDOC, oRDERNO='$oRDERNO', oRDERTIME='$oRDERTIME', pLANTCODE='$pLANTCODE', qTY=$qTY, rEGION='$rEGION', sTATUS='$sTATUS', uNIT='$uNIT')"
    }
}