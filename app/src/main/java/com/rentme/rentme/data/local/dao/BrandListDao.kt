package com.rentme.rentme.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.MainPage

@Dao
interface BrandListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBrandData(brandListEntity: BrandListEntity)

    @Query("SELECT * FROM `model_list`")
    suspend fun getModelsListName(): List<ModelsListEntity>


}