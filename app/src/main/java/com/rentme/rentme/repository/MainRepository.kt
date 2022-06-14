package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.WelcomeData
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val mainPageDao: MainPageDao
) {
    suspend fun getMainData(count: Int) = apiService.getMainDetails(count)

    suspend fun saveMainDataToLocal(mainPageData: WelcomeData) = mainPageDao.saveMainData(mainPageData)

    suspend fun getMainDateFromLocal() = mainPageDao.getMainData()
}