package com.rentme.rentme.data.remote

import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.UploadAdvertisement
import com.rentme.rentme.model.UploadAdvertisementResp
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseList
import retrofit2.http.*
import java.io.File

interface ApiService {

    @GET("main-page")
    suspend fun getMainDetails(): MainPage

    @GET("transport-model/list-details")
    suspend fun getModelLists(): BaseResponse<BaseResponseList<String>>

    @POST("/advertisement/create")
    suspend fun createAdvertisement(@Body advertisement: UploadAdvertisement) : UploadAdvertisementResp

    @Multipart
    @POST("/file")
    suspend fun createFile(@Part("files[]") files: List<File>) : List<String>

}