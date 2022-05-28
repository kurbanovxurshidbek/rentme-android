package com.rentme.rentme.di

import com.rentme.rentme.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerModule {
    var IS_TESTER = true
    private const val SERVER_DEVELOPMENT = "http://198.58.123.235:8080/"
    private const val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun server() = if (IS_TESTER) SERVER_DEVELOPMENT else SERVER_PRODUCTION

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit = Retrofit.Builder().baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun apiService(): ApiService = retrofitClient().create(ApiService::class.java)
}