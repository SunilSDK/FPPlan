package com.rtc.feedprodution.model.response.masters


import com.google.gson.annotations.SerializedName

data class TSTOCKDETAILS(
    @SerializedName("CREATED_ON")
    var cREATEDON: String,
    @SerializedName("DOC_ID")
    var dOCID: Int,
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: String,
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String,
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String,
    @SerializedName("PLANT_NAME")
    var pLANTNAME: String,
    @SerializedName("QTY")
    var qTY: Double,
    @SerializedName("STATUS")
    var sTATUS: String,
    @SerializedName("STOCK_POSTING_DATE")
    var sTOCKPOSTINGDATE: String,
    @SerializedName("UNIT")
    var uNIT: String


) {
    override fun toString(): String {
        return "TSTOCKDETAILS(cREATEDON='$cREATEDON', dOCID=$dOCID, mATERIALCODE='$mATERIALCODE', mATERIALNAME='$mATERIALNAME', pLANTCODE='$pLANTCODE', pLANTNAME='$pLANTNAME', qTY=$qTY, sTATUS='$sTATUS', sTOCKPOSTINGDATE='$sTOCKPOSTINGDATE', uNIT='$uNIT')"
    }
}