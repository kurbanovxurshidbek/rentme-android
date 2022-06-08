package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.local.dao.ModelsHistoryDao
import com.rentme.rentme.data.local.dao.ModelsListDao
import com.rentme.rentme.data.local.entity.ModelsHistory
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.ModelsList
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val modelsHistoryDao: ModelsHistoryDao,
    private val modelsListDao: ModelsListDao
) {
    suspend fun saveModelsHistory(name: ModelsHistory) = modelsHistoryDao.saveModelsHistory(name)

    suspend fun getModelsHistory() = modelsHistoryDao.getModelsHistory()

    suspend fun getModelsListFromLocal() = modelsListDao.getModelsListName()
}