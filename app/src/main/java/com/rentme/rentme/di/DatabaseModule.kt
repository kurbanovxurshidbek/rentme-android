package com.rentme.rentme.di

import android.content.Context
import androidx.room.Room
import com.rentme.rentme.data.local.MainPageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): MainPageDatabase =
        Room.databaseBuilder(context, MainPageDatabase::class.java, "mainData.db").build()

    @Provides
    @Singleton
    fun getMainPageDao(database: MainPageDatabase) = database.getMainPageDao()

    @Provides
    @Singleton
    fun getHistoryDao(database: MainPageDatabase) = database.getHistoryDao()
}