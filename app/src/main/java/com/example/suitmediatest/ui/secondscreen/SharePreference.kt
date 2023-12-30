package com.example.suitmediatest.ui.secondscreen

import android.content.Context
import android.content.SharedPreferences

class SharePreference(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPref", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        const val KEY_USER_NAME = "username"
    }

    fun saveUsername(userId: String) {
        editor.putString(KEY_USER_NAME, userId)
        editor.apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}
