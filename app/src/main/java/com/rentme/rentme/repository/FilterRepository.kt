package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.HistoryDao
import com.rentme.rentme.data.local.dao.MainPageDao
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.FilterPage
import com.rentme.rentme.model.MainPage
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getFilterResult(filterPage: FilterPage) = apiService.getFilterResult(filterPage)
}