package com.rtc.feedprodution.util

import android.content.Context
import android.preference.PreferenceManager

import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("DEPRECATION")
@Singleton
open class MyPreference @Inject
constructor(@ApplicationContext context : Context){
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getUserID(): String {
        return prefs.getString("UserID", "")!!
    }
    fun setUserID(UserID: String) {
        prefs.edit().putString("UserID", UserID).apply()
    }

    fun getToken(): String {
        return prefs.getString("Token", "")!!
    }

    fun setToken(UserID: String) {
        prefs.edit().putString("Token", UserID).apply()
    }
}