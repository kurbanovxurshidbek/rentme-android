package com.rentme.rentme.model.filtermodel

data class Advertisement(
    val category: String? = null,
    val createdAt: String? = null,
    val createdBy: Int? = null,
    val description: String? = null,
    val id: Int? = null,
    val location: Location? = null,
    val maxDuration: Int? = null,
    val minDuration: Int? = null,
    val prices: List<Price>? = null,
    val startDate: String? = null,
    val transport: Transport? = null
)