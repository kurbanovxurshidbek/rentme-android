package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.BrandListDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val mainPageDao: MainPageDao,
    private val brandListDao: BrandListDao
) {
    suspend fun getMainData(count: Int) = apiService.getMainDetails(count)

    suspend fun getMainPageDataFromLocal() = mainPageDao.getMainData()
}