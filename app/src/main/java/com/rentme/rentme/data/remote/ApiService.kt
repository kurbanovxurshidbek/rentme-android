package com.rentme.rentme.data.remote

import com.rentme.rentme.model.FileResponse
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.UploadAdvertisement
import com.rentme.rentme.model.UploadAdvertisementResp
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    companion object {
        const val Access_key: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTk1NTQ1ODUyIiwicm9sZXMiOlsiVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjU2NTc4Njk0fQ.0slGvfAw-VKsfO_Wmq9WNnyDDQh8s3Ft2rDElHrlRe4"
        const val key: String = "Authorization"
    }

    @GET("main-page")
    suspend fun getMainDetails(): MainPage

    @Headers("$key: $Access_key")
    @POST("/advertisement/create")
    suspend fun createAdvertisement(@Body advertisement: UploadAdvertisement) : UploadAdvertisementResp

    @Headers("$key: $Access_key")
    @Multipart
    @POST("/file")
    suspend fun createFile(@Part file: List<MultipartBody.Part>) : FileResponse

}