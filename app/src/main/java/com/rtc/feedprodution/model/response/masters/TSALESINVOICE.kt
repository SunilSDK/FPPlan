package com.rtc.feedprodution.model.response.masters


import com.google.gson.annotations.SerializedName

data class TSALESINVOICE(
    @SerializedName("BILLING_ITEM")
    var bILLINGITEM: String,
    @SerializedName("BILLING_TYPE")
    var bILLINGTYPE: String,
    @SerializedName("CITY")
    var cITY: String,
    @SerializedName("COMPANY_CODE")
    var cOMPANYCODE: String,
    @SerializedName("CREATED_ON")
    var cREATEDON: String,
    @SerializedName("CUST_CODE")
    var cUSTCODE: String,
    @SerializedName("CUST_NAME")
    var cUSTNAME: String,
    @SerializedName("DOC_ID")
    var dOCID: Int,
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: String,
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String,
    @SerializedName("MOBILE_NO")
    var mOBILENO: String,
    @SerializedName("MODIFIED_ON")
    var mODIFIEDON: Any,
    @SerializedName("OUTBOUND_NO")
    var oUTBOUNDNO: String,
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String,
    @SerializedName("PLANT_NAME")
    var pLANTNAME: String,
    @SerializedName("QTY")
    var qTY: String,
    @SerializedName("REGION")
    var rEGION: String,
    @SerializedName("SALE_INVOICE_DATE")
    var sALEINVOICEDATE: String,
    @SerializedName("SALE_INVOICE_NO")
    var sALEINVOICENO: String,
    @SerializedName("SALE_ORDER_NO")
    var sALEORDERNO: String,
    @SerializedName("STATUS")
    var sTATUS: String,
    @SerializedName("VEHICLE_NO")
    var vEHICLENO: String


) {
    override fun toString(): String {
        return "TSALESINVOICE(bILLINGITEM='$bILLINGITEM', bILLINGTYPE='$bILLINGTYPE', cITY='$cITY', cOMPANYCODE='$cOMPANYCODE', cREATEDON='$cREATEDON', cUSTCODE='$cUSTCODE', cUSTNAME='$cUSTNAME', dOCID=$dOCID, mATERIALCODE='$mATERIALCODE', mATERIALNAME='$mATERIALNAME', mOBILENO='$mOBILENO', mODIFIEDON=$mODIFIEDON, oUTBOUNDNO='$oUTBOUNDNO', pLANTCODE='$pLANTCODE', pLANTNAME='$pLANTNAME', qTY='$qTY', rEGION='$rEGION', sALEINVOICEDATE='$sALEINVOICEDATE', sALEINVOICENO='$sALEINVOICENO', sALEORDERNO='$sALEORDERNO', sTATUS='$sTATUS', vEHICLENO='$vEHICLENO')"
    }
}