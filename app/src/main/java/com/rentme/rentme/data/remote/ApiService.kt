package com.rentme.rentme.data.remote

import com.rentme.rentme.model.MainPage
import retrofit2.http.GET

interface ApiService {

    @GET("main-page")
    suspend fun getMainDetails(): MainPage
}