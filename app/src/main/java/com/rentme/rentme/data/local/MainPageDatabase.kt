package com.rentme.rentme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rentme.rentme.data.local.converter.ErrorTypeConverter
import com.rentme.rentme.data.local.converter.MainTypeConverter
import com.rentme.rentme.data.local.converter.ModelConverter
import com.rentme.rentme.data.local.dao.*
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.data.local.entity.ModelsHistory
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.MainPage

@Database(entities = [MainPage::class, History::class,ModelsListEntity::class,ModelsHistory::class, BrandListEntity::class], version = 1, exportSchema = false)
@TypeConverters(MainTypeConverter::class, ErrorTypeConverter::class, ModelConverter::class)
abstract class MainPageDatabase: RoomDatabase() {
    abstract fun getMainPageDao(): MainPageDao

    abstract fun getHistoryDao(): HistoryDao

    abstract fun getModelsListDao(): ModelsListDao

    abstract fun getModelsHistoryDao(): ModelsHistoryDao

    abstract fun getBrandListDao(): BrandListDao

}
