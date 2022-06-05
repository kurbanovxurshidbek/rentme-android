package com.rentme.rentme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.data.local.entity.MainPageData
import com.rentme.rentme.model.MainPage

@Database(entities = [MainPage::class, History::class], version = 1, exportSchema = false)
@TypeConverters(MainTypeConverter::class)
abstract class MainPageDatabase: RoomDatabase() {
    abstract fun getMainPageDao(): MainPageDao

    abstract fun getHistoryDao(): HistoryDao
}