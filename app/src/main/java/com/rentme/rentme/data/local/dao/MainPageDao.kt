package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rentme.rentme.model.WelcomeData

@Dao
interface MainPageDao {
    @Insert
    suspend fun saveMainData(mainPage: WelcomeData)

    @Query("SELECT * FROM mainData")
    suspend fun getMainData(): WelcomeData
}