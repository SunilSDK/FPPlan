package com.rtc.feedprodution.model.response.masters


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MMATERIAL(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @SerializedName("CREATED_BY")
    var cREATEDBY: String,
    @SerializedName("CREATED_DATE")
    var cREATEDDATE: String,
    @SerializedName("IS_DELETED")
    var iSDELETED: Boolean,
    @SerializedName("MATERIAL_CODE")
    var mATERIALCODE: Int,
    @SerializedName("MATERIAL_NAME")
    var mATERIALNAME: String,
    @SerializedName("MODIFIED_BY")
    var mODIFIEDBY: Any,
    @SerializedName("MODIFIED_DATE")
    var mODIFIEDDATE: Any,
    @SerializedName("SAP_CODE")
    var sAPCODE: String,
    @SerializedName("UNIT_ID")
    var uNITID: Int



) {
    override fun toString(): String {
        return "MMATERIAL(cREATEDBY='$cREATEDBY', cREATEDDATE='$cREATEDDATE', iSDELETED=$iSDELETED, mATERIALCODE=$mATERIALCODE, mATERIALNAME='$mATERIALNAME', mODIFIEDBY=$mODIFIEDBY, mODIFIEDDATE=$mODIFIEDDATE, sAPCODE='$sAPCODE', uNITID=$uNITID)"
    }
}