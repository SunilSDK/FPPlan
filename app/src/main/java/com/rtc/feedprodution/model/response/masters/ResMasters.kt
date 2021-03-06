package com.rtc.feedprodution.model.response.masters


import com.google.gson.annotations.SerializedName

data class ResMasters(
    @SerializedName("M_EMPLOYEE_PLANT")
    var mEMPLOYEEPLANT: List<MEMPLOYEEPLANT>,
    @SerializedName("M_MATERIAL")
    var mMATERIAL: List<MMATERIAL>,
    @SerializedName("M_PLANT")
    var mPLANT: List<MPLANT>,
    @SerializedName("T_ORDER_DEAILS")
    var tORDERDEAILS: List<TORDERDEAILS>,
    @SerializedName("T_SALES_INVOICE")
    var tSALESINVOICE: List<TSALESINVOICE>,
    @SerializedName("T_STOCK_DETAILS")
    var tSTOCKDETAILS: List<TSTOCKDETAILS>


) {
    override fun toString(): String {
        return "ResMasters(mEMPLOYEEPLANT=$mEMPLOYEEPLANT, mMATERIAL=$mMATERIAL, mPLANT=$mPLANT, tORDERDEAILS=$tORDERDEAILS, tSALESINVOICE=$tSALESINVOICE, tSTOCKDETAILS=$tSTOCKDETAILS)"
    }
}