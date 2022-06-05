package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rentme.rentme.data.local.entity.History

@Dao
interface HistoryDao {
    @Insert
    suspend fun saveHistory(location: History)

    @Query("SELECT * FROM history")
    suspend fun getHistory(): List<History>
}