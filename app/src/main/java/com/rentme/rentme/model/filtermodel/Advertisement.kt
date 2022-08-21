package com.rentme.rentme.model.filtermodel

import com.rentme.rentme.model.Location

data class Advertisement(
    val category: String? = null,
    val createdAt: String? = null,
    val createdBy: Int? = null,
    val description: String? = null,
    val id: Int? = null,
    val location: Location? = null,
    val maxDuration: Int = 0,
    val minDuration: Int = 0,
    val prices: List<Price>? = null,
    val startDate: String? = null,
    val transport: Transport? = null
)