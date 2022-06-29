package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.BrandListDao
import com.rentme.rentme.data.local.dao.ModelsListDao
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.data.remote.ApiService
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val apiService: ApiService,
    private val modelsListDao: ModelsListDao,
    private val brandListDao: BrandListDao
){

    suspend fun getModelsList() = apiService.getModelLists()

    suspend fun saveModelsListToLocal(modelsListEntity: ModelsListEntity) = modelsListDao.saveModelsListData(modelsListEntity)

    suspend fun getBrandListToAPI() = apiService.getBrandList(50, 0)

    suspend fun saveBrandListToLocal(brandListEntity: BrandListEntity) = brandListDao.saveBrandData(brandListEntity)

}