package com.rentme.rentme.repository

import com.rentme.rentme.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMainData() = apiService.getMainDetails()
}