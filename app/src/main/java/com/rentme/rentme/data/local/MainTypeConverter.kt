package com.rentme.rentme.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rentme.rentme.model.MainPage

class MainTypeConverter {
    @TypeConverter
    fun fromPhoto(photoItem: MainPage): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhoto(json: String): MainPage {
        return Gson().fromJson(json, MainPage::class.java)
    }
}