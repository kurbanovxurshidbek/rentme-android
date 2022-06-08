package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.ModelsHistoryDao
import com.rentme.rentme.data.local.entity.ModelsHistory
import javax.inject.Inject

class ModelsHistoryRepository @Inject constructor(private val modelsHistoryDao: ModelsHistoryDao) {
    suspend fun saveModelsHistory(name: ModelsHistory) = modelsHistoryDao.saveModelsHistory(name)

    suspend fun getModelsHistory() = modelsHistoryDao.getModelsHistory()
}