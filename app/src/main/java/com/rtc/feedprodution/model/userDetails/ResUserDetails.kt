package com.rtc.feedprodution.model.userDetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class ResUserDetails(

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Int,

	@SerializedName("CURRENT_VER")
	@Expose
	var cURRENTVER: String? = null,

	@SerializedName("CREATED_BY")
	@Expose
	var cREATEDBY: String? = null,

	@SerializedName("IS_DELETED")
	@Expose
	var iSDELETED: String? = null,

	@SerializedName("EMPLOYEE_ID")
	@Expose
	var eMPLOYEEID: String? = null,

	@SerializedName("IMEI_NO")
	@Expose
	var iMEINO: String? = null,

	@SerializedName("MODIFIED_BY")
	@Expose
	var mODIFIEDBY: String? = null,

	@SerializedName("CREATED_DATE")
	@Expose
	var cREATEDDATE: String? = null,

	@SerializedName("LATEST_VER")
	@Expose
	var lATESTVER: String? = null,

	@SerializedName("EMAIL_ADDR")
	@Expose
	var eMAILADDR: String? = null,

	@SerializedName("DESIGNATION_ID")
	@Expose
	var dESIGNATIONID: String? = null,

	@SerializedName(".expires")
	@Expose
	var expires: String? = null,

	@SerializedName("EMPLOYEE_NAME")
	@Expose
	var eMPLOYEENAME: String? = null,

	@SerializedName("USER_NAME")
	@Expose
	var uSERNAME: String? = null,

	@SerializedName("token_type")
	@Expose
	var tokenType: String? = null,

	@SerializedName("MODIFIED_DATE")
	@Expose
	var mODIFIEDDATE: String? = null,

	@SerializedName(".issued")
	@Expose
	var issued: String? = null,

	@SerializedName("access_token")
	@Expose
	var accessToken: String? = null,

	@SerializedName("PASSWORD")
	@Expose
	var pASSWORD: String? = null,

	@SerializedName("CONTACT_NO_1")
	@Expose
	var cONTACTNO1: String? = null,

	@SerializedName("CONTACT_NO_2")
	@Expose
	var cONTACTNO2: String? = null,

	@SerializedName("expires_in")
	@Expose
	var expiresIn: Int? = null


) {
	override fun toString(): String {
		return "ResUserDetails(id=$id, cURRENTVER=$cURRENTVER, cREATEDBY=$cREATEDBY, iSDELETED=$iSDELETED, eMPLOYEEID=$eMPLOYEEID, iMEINO=$iMEINO, mODIFIEDBY=$mODIFIEDBY, cREATEDDATE=$cREATEDDATE, lATESTVER=$lATESTVER, eMAILADDR=$eMAILADDR, dESIGNATIONID=$dESIGNATIONID, expires=$expires, eMPLOYEENAME=$eMPLOYEENAME, uSERNAME=$uSERNAME, tokenType=$tokenType, mODIFIEDDATE=$mODIFIEDDATE, issued=$issued, accessToken=$accessToken, pASSWORD=$pASSWORD, cONTACTNO1=$cONTACTNO1, cONTACTNO2=$cONTACTNO2, expiresIn=$expiresIn)"
	}
}
