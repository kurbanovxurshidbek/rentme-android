package com.rentme.rentme.model

import java.io.Serializable

data class UploadAdvertisement(
    var description: String? = null,
    var prices: List<Price>? = null,
    val category: String? = null,
    val location: Location? = null,
    val startDate: String? = null,
    val minDuration: Long? = null,
    val maxDuration: Long? = null,
    var transport: Transport? = null
)

data class UploadAdvertisementResp(
    val accessTokenExpiry: Long,
    val refreshTokenExpiry: Long,
    val issuedAt: Long,
    val accessToken: String,
    val refreshToken: String,
    val first: Boolean
)

data class Price(
    val quantity: Int? = null,
    val type: String? = null
)

data class Transport(
    val transportType: String? = null,
    val model: String? = null,
    val year: Long? = null,
    val transmission: String? = null,
    val fuelType: String? = null,
    val color: String? = null,
    val pictures: List<Picture>? = null,
    val wellEquipped: Boolean? = null
)

