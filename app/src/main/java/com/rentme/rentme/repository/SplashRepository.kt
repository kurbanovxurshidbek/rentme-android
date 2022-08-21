package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.BrandListDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.local.dao.ModelsListDao
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.MainPage
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val apiService: ApiService,
    private val modelsListDao: ModelsListDao,
    private val brandListDao: BrandListDao,
    private val mainPageDao: MainPageDao
){

    suspend fun getModelsList() = apiService.getModelLists()

    suspend fun saveModelsListToLocal(modelsListEntity: ModelsListEntity) = modelsListDao.saveModelsListData(modelsListEntity)

    suspend fun getBrandListToAPI() = apiService.getBrandList(50, 0)

    suspend fun saveBrandListToLocal(brandListEntity: BrandListEntity) = brandListDao.saveBrandData(brandListEntity)

    suspend fun saveMainPageToLocal(mainPage: MainPage) = mainPageDao.saveMainData(mainPage)

    suspend fun updateMainPage(mainPage: MainPage) = mainPageDao.updateAndSaveMainData(mainPage)

    suspend fun getMainPageDataFromLocal() = mainPageDao.getMainDataList()

    suspend fun getMainPageDataFromServer() = apiService.getMainDetails()
}