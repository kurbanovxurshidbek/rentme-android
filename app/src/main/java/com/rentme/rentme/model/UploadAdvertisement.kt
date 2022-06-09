package com.rentme.rentme.model

import java.io.Serializable

data class UploadAdvertisement(
    val description: String? = null,
    val prices: List<Price>? = null,
    val category: String? = null,
    val location: Location? = null,
    val startDate: String? = null,
    val minDuration: Long? = null,
    val maxDuration: Long? = null,
    val transport: Transport? = null
) : Serializable

data class UploadAdvertisementResp(
    val accessTokenExpiry: Long,
    val refreshTokenExpiry: Long,
    val issuedAt: Long,
    val accessToken: String,
    val refreshToken: String,
    val first: Boolean
)

data class Price(
    val quantity: Long? = null,
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

