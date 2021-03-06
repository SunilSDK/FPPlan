package com.rtc.feedprodution.model.response.vehiclewiseq


import com.google.gson.annotations.SerializedName

data class TORDERDEAILS(
    @SerializedName("BILLING_ITEM")
    var bILLINGITEM: String, // 000010
    @SerializedName("BILLING_TYPE")
    var bILLINGTYPE: String, // ZFF2
    @SerializedName("CITY")
    var cITY: String, // TAL MOHOL, DIST SOLAPUR
    @SerializedName("CREATED_ON")
    var cREATEDON: String, // 2021-01-15T10:16:01.14
    @SerializedName("CUSTOMER_CODE")
    var cUSTOMERCODE: String, // 501113
    @SerializedName("CUSTOMER_NAME")
    var cUSTOMERNAME: String, // YASHRAJ POULTRY SERVICES
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: String, // 200087
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String, // DESHI FINISHER
    @SerializedName("OPEN_QTY")
    var oPENQTY: Double, // 0.0
    @SerializedName("ORDER_DATE_TIME")
    var oRDERDATETIME: String, // 2021-01-15T09:53:30
    @SerializedName("ORDER_DOC")
    var oRDERDOC: Int, // 1
    @SerializedName("ORDER_NO")
    var oRDERNO: String, // 0001543611
    @SerializedName("ORDER_TIME")
    var oRDERTIME: String, // 09:53:30
    @SerializedName("OUTBOUND_NO")
    var oUTBOUNDNO: String, // 0081703905
    @SerializedName("PLANT_CODE")
    var pLANTCODE: String, // FM02
    @SerializedName("QTY")
    var qTY: Double, // 5.0
    @SerializedName("REGION")
    var rEGION: String, // Maharashtra
    @SerializedName("SALE_INVOICE_DATE")
    var sALEINVOICEDATE: String, // 2021-01-15T00:00:00
    @SerializedName("SALE_INVOICE_NO")
    var sALEINVOICENO: String, // 0091653196
    @SerializedName("SALES_QTY")
    var sALESQTY: Double, // 5.0
    @SerializedName("STATUS")
    var sTATUS: String,
    @SerializedName("UNIT")
    var uNIT: String // BAG


) {
    override fun toString(): String {
        return "TORDERDEAILS(bILLINGITEM='$bILLINGITEM', bILLINGTYPE='$bILLINGTYPE', cITY='$cITY', cREATEDON='$cREATEDON', cUSTOMERCODE='$cUSTOMERCODE', cUSTOMERNAME='$cUSTOMERNAME', mATERIALCODE='$mATERIALCODE', mATERIALNAME='$mATERIALNAME', oPENQTY=$oPENQTY, oRDERDATETIME='$oRDERDATETIME', oRDERDOC=$oRDERDOC, oRDERNO='$oRDERNO', oRDERTIME='$oRDERTIME', oUTBOUNDNO='$oUTBOUNDNO', pLANTCODE='$pLANTCODE', qTY=$qTY, rEGION='$rEGION', sALEINVOICEDATE='$sALEINVOICEDATE', sALEINVOICENO='$sALEINVOICENO', sALESQTY=$sALESQTY, sTATUS='$sTATUS', uNIT='$uNIT')"
    }
}