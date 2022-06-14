package com.rentme.rentme.model

data class UploadAdvertisement(
    var description: String? = null,
    var prices: List<Price>? = null,
    val category: Category? = null,
    val location: Location? = null,
    var startDate: String? = null,
    val minDuration: Int? = null,
    val maxDuration: Int? = null,
    var transport: TransportUpload? = null
)

enum class Category{
    CAR,
    TRUCK,
    MOTORBIKE
}

data class UploadAdvertisementResp(
    val data: UploadARespHelper? = null,
    val status: Int? = null
)

data class UploadARespHelper(
    val data: Int? = null,
    val success: Boolean? = null,
    val totalCount: Int? = null,
)

data class Price(
    val quantity: Int? = null,
    val type: Type? = null
)

enum class Type{
    MONTHLY,
    DAILY
}


data class TransportUpload(
    val model: String? = null,
    val year: Int? = null,
    val transmission: Transmission? = null,
    val fuelType: FuelType? = null,
    val color: String? = null,
    val pictures: List<Picture>? = null,
    val wellEquipped: Boolean? = null
)

enum class Transmission{
    AUTOMATIC,
    MANUAL
}

enum class FuelType{
    PETROL,
    GAS,
    ELECTRICITY
}