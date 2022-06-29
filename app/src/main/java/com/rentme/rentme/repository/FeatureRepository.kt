package com.rentme.rentme.repository

import com.rentme.rentme.data.local.dao.BrandListDao
import com.rentme.rentme.data.remote.ApiService
import com.rentme.rentme.model.UploadAdvertisement
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class FeatureRepository @Inject constructor(
    private val apiService: ApiService,
    private val brandListDao: BrandListDao
) {
    suspend fun createAdvertisement(advertisement: UploadAdvertisement) = apiService.createAdvertisement(advertisement)

    suspend fun createFile(files: List<MultipartBody.Part>) = apiService.createFile(files)

    suspend fun getBrandList() = brandListDao.getBrandList()
}