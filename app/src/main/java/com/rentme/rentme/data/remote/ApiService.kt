package com.rentme.rentme.data.remote

import com.rentme.rentme.model.*
import com.rentme.rentme.model.filtermodel.Advertisement
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseObject
import com.rentme.rentme.model.base.BaseResponseList
import com.rentme.rentme.model.base.DataLimit
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @GET("main-page")
    suspend fun getMainDetails(count: Int): BaseResponse<BaseResponseObject<MainPage>>

    @POST("advertisement/search")
    suspend fun getFilterResult(@Body filterPage: FilterPage): BaseResponse<BaseResponseList<Advertisement>>


    @POST("advertisement/create")
    suspend fun createAdvertisement(@Body advertisement: UploadAdvertisement) : UploadAdvertisementResp

    @Multipart
    @POST("file")
    suspend fun createFile(@Part file: List<MultipartBody.Part>) : FileResponse

    @GET("transport-model/list")
    suspend fun getModelLists(): BaseResponse<BaseResponseList<String>>

    @GET("advertisement/list-saved")
    suspend fun getFavouriteModels(@Query("size") size: Int = 5, @Query("page") page: Int = 0): BaseResponse<BaseResponseList<FavouriteModel>>

    @GET("brand/list")
    suspend fun getBrandList(@Query("size") size: Int, @Query("page") page: Int): BaseResponse<BaseResponseList<Brands>>

    @POST("advertisement/list_my")
    suspend fun getMyAdvertisementList(@Body dataLimit: DataLimit): BaseResponse<BaseResponseList<Advertisement>>

    @DELETE("advertisement/delete/{id}")
    suspend fun deleteAdvertisement(@Path("id") id: Int) : BaseResponse<BaseResponseObject<Boolean>>

}