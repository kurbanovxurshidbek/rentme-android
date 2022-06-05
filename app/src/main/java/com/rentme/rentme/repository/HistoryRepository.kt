package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.entity.History
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val historyDao: HistoryDao) {
    suspend fun saveHistory(location: History) = historyDao.saveHistory(location)

    suspend fun getHistory() = historyDao.getHistory()
}