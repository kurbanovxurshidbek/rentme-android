package com.rentme.rentme.di

import com.rentme.rentme.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerModule {
    var IS_TESTER = true
    private const val SERVER_DEVELOPMENT = "http://198.58.123.235:8080/"
    private const val SERVER_PRODUCTION = "http://198.58.123.235:8080/"

    @Provides
    fun server() = if (IS_TESTER) SERVER_DEVELOPMENT else SERVER_PRODUCTION

    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(server())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout( 60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Content-Type", "application/json")
//            builder.header("Accept", "application/json")
            builder.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTkwMzYyNjA5Iiwicm9sZXMiOlsiVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjU4NTQ1MTMxfQ.ge3t3oTT8oqMqFH7svmOEFraBWLhFrrSXVhBTJ0dLtw")
            //   if (sharedPref.user != ""){
            //     builder.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTQxMTEwNzE3Iiwicm9sZXMiOlsiQ0xJRU5UIl0sImV4cCI6MTY1NTA0OTkyMn0.jmnpKdM0Dub-ylE_LesdGWXpmzhHGGr5DcrZj7bRYJI")
            //  }
            chain.proceed(builder.build())
        })
        .build()

}