package com.rentme.rentme.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rentme.rentme.model.Model
import java.lang.reflect.Type
import java.util.ArrayList

class ModelConverter {
    @TypeConverter
    fun fromModelList(models: List<Model>): String {
        return Gson().toJson(models)
    }

    @TypeConverter
    fun toModelList(json: String): List<Model> {
        val type: Type = object : TypeToken<ArrayList<Model>>() {}.type
        return Gson().fromJson(json, type)
    }
}