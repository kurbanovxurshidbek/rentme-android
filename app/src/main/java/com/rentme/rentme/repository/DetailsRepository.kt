package com.rentme.rentme.repository

import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.base.DataLimit
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun deleteAdvertisement(id: Int) = apiService.deleteAdvertisement(id)

}