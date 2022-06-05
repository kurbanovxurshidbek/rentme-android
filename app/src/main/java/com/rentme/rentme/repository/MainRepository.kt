package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.MainPage
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val mainPageDao: MainPageDao
) {
    suspend fun getMainData() = apiService.getMainDetails()

    suspend fun saveMainDataToLocal(mainPageData: MainPage) = mainPageDao.saveMainData(mainPageData)

    suspend fun getMainDateFromLocal() = mainPageDao.getMainData()
}