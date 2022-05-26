package com.rentme.rentme.model

data class MainPage(
    val data: WelcomeData? = null,
    val status: Long? = null
)

data class WelcomeData(
    val data: DataData? = null,
    val error: Error? = null,
    val success: Boolean? = null,
    val totalCount: Long? = null
)

data class DataData(
    val picturePathList: List<String>? = null,
    val lastAdvertisements: List<Advertisement>? = null,
    val dailyAdvertisements: List<Advertisement>? = null,
    val weeklyAdvertisements: List<Advertisement>? = null
)

data class Advertisement(
    val id: Long? = null,
    val description: String? = null,
    val prices: List<Price>? = null,
    val picture: String? = null,
    val category: String? = null,
    val transportDTO: TransportDTO? = null
)

data class Price(
    val id: Long? = null,
    val quantity: Long? = null,
    val type: String? = null
)

data class TransportDTO(
    val type: Type? = null,
    val model: String? = null,
    val year: Long? = null,
    val transmission: String? = null,
    val fuelType: String? = null,
    val color: String? = null,
    val pictures: List<Picture>? = null,
    val wellEquipped: Boolean? = null
)

data class Picture(
    val path: String? = null,
    val main: Boolean? = null
)

data class Type(
    val id: Long? = null,
    val createdAt: String? = null,
    val createdBy: Long? = null,
    val updatedAt: String? = null,
    val updatedBy: Long? = null,
    val deleted: Boolean? = null,
    val name: String? = null,
    val category: String? = null,
    val imagePath: String? = null
)

data class Error(
    val timestamp: String? = null,
    val status: Long? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null
)
