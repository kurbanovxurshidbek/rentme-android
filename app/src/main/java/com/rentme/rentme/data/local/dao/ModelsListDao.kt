package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.MainPage

@Dao
interface ModelsListDao {
    @Insert
    suspend fun saveModelsListData(modelsListEntity: ModelsListEntity)

    @Query("SELECT * FROM `model_list`")
    suspend fun getModelsListName(): List<ModelsListEntity>


}