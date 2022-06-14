package com.rentme.rentme.model

data class FavouriteModel (
    val id: Long? = null,
    val description: String? = null,
    val prices: List<Price>? = null,
    val category: String? = null,
    val location: Location? = null,
    val startDate: String? = null,
    val minDuration: Long? = null,
    val maxDuration: Long? = null,
    val transport: Transport? = null,
    val createdAt: String? = null,
    val createdBy: Long? = null
)
