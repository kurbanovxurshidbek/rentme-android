package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rentme.rentme.model.MainPage

@Dao
interface MainPageDao {
    @Insert
    suspend fun saveMainData(mainPage: MainPage)

    @Query("SELECT * FROM mainData")
    suspend fun getMainDataList(): List<MainPage>

    @Query("SELECT * FROM mainData WHERE id = 1 ORDER BY id ASC")
    suspend fun getMainData(): MainPage

    @Update
    suspend fun updateAndSaveMainData(mainPage: MainPage)
}