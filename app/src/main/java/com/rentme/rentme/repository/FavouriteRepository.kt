package com.rentme.rentme.repository

import com.rentme.rentme.data.remote.ApiService
import javax.inject.Inject

class FavouriteRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getFavouriteModelsFromServer() = apiService.getFavouriteModels()
}