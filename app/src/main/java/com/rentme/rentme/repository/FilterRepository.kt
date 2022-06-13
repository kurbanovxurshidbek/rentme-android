package com.rentme.rentme.repository

import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.FilterPage
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getFilterResult(filterPage: FilterPage) = apiService.getFilterResult(filterPage)
}