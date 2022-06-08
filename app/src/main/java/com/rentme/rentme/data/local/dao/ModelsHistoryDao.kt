package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.data.local.entity.ModelsHistory

@Dao
interface ModelsHistoryDao {
    @Insert
    suspend fun saveModelsHistory(name: ModelsHistory)

    @Query("SELECT * FROM models_history")
    suspend fun getModelsHistory(): List<ModelsHistory>
}