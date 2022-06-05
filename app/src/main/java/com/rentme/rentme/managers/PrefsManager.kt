package com.rentme.rentme.managers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsManager @Inject constructor(@ApplicationContext context: Context) {
    private val mySharedPref: SharedPreferences =
        context.getSharedPreferences("rentMePref", Context.MODE_PRIVATE)

    var token: String
        set(value) = mySharedPref.edit().putString("token", value).apply()
        get() = mySharedPref.getString("token", "")!!

    var locationHistoryList: ArrayList<String>
        set(value) {
            val gson = Gson()
            val json = gson.toJson(value)
            mySharedPref.edit().putString("locationHistory", json).apply()
        }
        get() {
            val gson = Gson()
            val json: String? = mySharedPref.getString("locationHistory", "")
            val type = object : TypeToken<ArrayList<String>>() {}.type

            return gson.fromJson(json, type)
        }

    fun saveArrayList(key: String, list: ArrayList<String>) {
        val prefsEditor = mySharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        prefsEditor.putString(key, json)
        prefsEditor.apply()
    }

    fun getArrayList(key: String): ArrayList<String>?{
        val gson = Gson()
        val json: String? = mySharedPref.getString(key, "")
        val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.type
        return gson.fromJson(json, type)
    }
}