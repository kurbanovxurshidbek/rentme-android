package com.rentme.rentme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.local.dao.ModelsHistoryDao
import com.rentme.rentme.data.local.dao.ModelsListDao
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.data.local.entity.ModelsHistory
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.WelcomeData

@Database(entities = [WelcomeData::class, History::class,ModelsListEntity::class,ModelsHistory::class], version = 1, exportSchema = false)
@TypeConverters(MainTypeConverter::class, ErrorTypeConverter::class)
abstract class MainPageDatabase: RoomDatabase() {
    abstract fun getMainPageDao(): MainPageDao

    abstract fun getHistoryDao(): HistoryDao

    abstract fun getModelsListDao(): ModelsListDao

    abstract fun getModelsHistoryDao(): ModelsHistoryDao

}
