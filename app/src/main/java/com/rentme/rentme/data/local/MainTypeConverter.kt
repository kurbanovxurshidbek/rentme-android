package com.rentme.rentme.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rentme.rentme.model.WelcomeData

class MainTypeConverter {
    @TypeConverter
    fun fromPhoto(photoItem: WelcomeData): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhoto(json: String): WelcomeData {
        return Gson().fromJson(json, WelcomeData::class.java)
    }
}