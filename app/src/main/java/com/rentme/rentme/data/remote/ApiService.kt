package com.rentme.rentme.data.remote

import com.rentme.rentme.model.MainPage
import retrofit2.http.GET

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