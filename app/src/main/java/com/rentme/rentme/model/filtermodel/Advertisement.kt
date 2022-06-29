package com.rentme.rentme.model.filtermodel

import com.rentme.rentme.model.Location

data class Advertisement(
    val category: String,
    val createdAt: String,
    val createdBy: Int,
    val description: String,
    val id: Int,
    val location: Location,
    val maxDuration: Int,
    val minDuration: Int,
    val prices: List<Price>,
    val startDate: String,
    val transport: Transport
)