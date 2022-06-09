package com.rentme.rentme.data.remote

import com.rentme.rentme.model.Car
import com.rentme.rentme.model.FilterPage
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.filtermodel.Filter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("main-page")
    suspend fun getMainDetails(): MainPage

    @POST("advertisement/search")
    suspend fun getFilterResult(@Body filterPage: FilterPage): Filter

}