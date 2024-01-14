package com.example.assessment.activity.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object Preference {

    private fun getPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun getEdit(context: Context): SharedPreferences.Editor {
        return getPreference(context).edit()
    }

    fun saveUserDetails(context: Context, name: String, email: String, dob: String, phone: String, address: String) {
        val edit = getEdit(context)
        edit.putString("USER_NAME", name)
        edit.putString("USER_EMAIL", email)
        edit.putString("USER_DOB", dob)
        edit.putString("USER_PHONE", phone)
        edit.putString("USER_ADDRESS", address)
        edit.apply()
    }

    fun getName(context: Context): String {
        val preferences = getPreference(context)
        return preferences.getString("USER_NAME", "") ?: ""
    }

    fun getEmail(context: Context): String {
        val preferences = getPreference(context)
        return preferences.getString("USER_EMAIL", "") ?: ""
    }

    fun getDob(context: Context): String {
        val preferences = getPreference(context)
        return preferences.getString("USER_DOB", "") ?: ""
    }

    fun getPhone(context: Context): String {
        val preferences = getPreference(context)
        return preferences.getString("USER_PHONE", "") ?: ""
    }

    fun getAddress(context: Context): String {
        val preferences = getPreference(context)
        return preferences.getString("USER_ADDRESS", "") ?: ""
    }
}
