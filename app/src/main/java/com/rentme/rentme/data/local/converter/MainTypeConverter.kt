package com.rentme.rentme.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rentme.rentme.model.Brands
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.filtermodel.Advertisement
import java.util.ArrayList

class MainTypeConverter {
    @TypeConverter
    fun fromAdvertisements(photoItem: List<Advertisement>): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toAdvertisements(json: String): List<Advertisement> {
//        return Gson().fromJson(json, List<Advertisement>::class.java)
        return Gson().fromJson(json, object : TypeToken<List<Advertisement>>() {}.type)
    }

    @TypeConverter
    fun fromPhotosList(photos: List<String>): String{
        return Gson().toJson(photos)
    }

    @TypeConverter
    fun toPhotosList(json: String): List<String>{
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromBrandsList(photos: List<Brands>): String{
        return Gson().toJson(photos)
    }

    @TypeConverter
    fun toBrandsList(json: String): List<Brands>{
        return Gson().fromJson(json, object : TypeToken<List<Brands>>() {}.type)
    }

}