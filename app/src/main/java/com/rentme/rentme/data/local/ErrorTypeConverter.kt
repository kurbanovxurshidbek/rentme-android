package com.rentme.rentme.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.base.Error

class ErrorTypeConverter {
    @TypeConverter
    fun fromPhoto(photoItem: com.rentme.rentme.model.base.Error): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhoto(json: String): com.rentme.rentme.model.base.Error {
        return Gson().fromJson(json, Error::class.java)
    }
}