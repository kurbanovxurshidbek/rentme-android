package com.rentme.rentme.model

data class UploadAdvertisement(
    var description: String? = null,
    var prices: List<Price>? = null,
    val category: String? = null,
    val location: Location? = null,
    var startDate: String? = null,
    val minDuration: Long? = null,
    val maxDuration: Long? = null,
    var transport: Transport? = null
)

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
    val type: String? = null
)

data class Transport(
    val model: String? = null,
    val year: Int? = null,
    val transmission: String? = null,
    val fuelType: String? = null,
    val color: String? = null,
    val pictures: List<Picture>? = null,
    val wellEquipped: Boolean? = null
)

