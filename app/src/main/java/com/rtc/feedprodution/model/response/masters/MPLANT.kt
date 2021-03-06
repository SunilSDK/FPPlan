package com.rtc.feedprodution.model.response.masters


import com.google.gson.annotations.SerializedName

data class MPLANT(
    @SerializedName("CAPICITY")
    var cAPICITY: Double,
    @SerializedName("COMPANY_CODE")
    var cOMPANYCODE: String,
    @SerializedName("CONTACT_NO_1")
    var cONTACTNO1: String,
    @SerializedName("CONTACT_NO_2")
    var cONTACTNO2: String,
    @SerializedName("CORPORATE_ADDR")
    var cORPORATEADDR: String,
    @SerializedName("CREATED_BY")
    var cREATEDBY: String,
    @SerializedName("CREATED_DATE")
    var cREATEDDATE: String,
    @SerializedName("EMAIL_ADDR")
    var eMAILADDR: String,
    @SerializedName("FAX_NO")
    var fAXNO: String,
    @SerializedName("IS_DELETED")
    var iSDELETED: Boolean,
    @SerializedName("MODIFIED_BY")
    var mODIFIEDBY: Any,
    @SerializedName("MODIFIED_DATE")
    var mODIFIEDDATE: Any,
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String,
    @SerializedName("PLANT_HEAD")
    var pLANTHEAD: String,
    @SerializedName("PLANT_HEAD_EMAIL_ADDR")
    var pLANTHEADEMAILADDR: String,
    @SerializedName("PLANT_NAME")
    var pLANTNAME: String,
    @SerializedName("REGISTERED_ADDR")
    var rEGISTEREDADDR: String


) {
    override fun toString(): String {
        return "MPLANT(cAPICITY=$cAPICITY, cOMPANYCODE='$cOMPANYCODE', cONTACTNO1='$cONTACTNO1', cONTACTNO2='$cONTACTNO2', cORPORATEADDR='$cORPORATEADDR', cREATEDBY='$cREATEDBY', cREATEDDATE='$cREATEDDATE', eMAILADDR='$eMAILADDR', fAXNO='$fAXNO', iSDELETED=$iSDELETED, mODIFIEDBY=$mODIFIEDBY, mODIFIEDDATE=$mODIFIEDDATE, pLANTCODE='$pLANTCODE', pLANTHEAD='$pLANTHEAD', pLANTHEADEMAILADDR='$pLANTHEADEMAILADDR', pLANTNAME='$pLANTNAME', rEGISTEREDADDR='$rEGISTEREDADDR')"
    }
}