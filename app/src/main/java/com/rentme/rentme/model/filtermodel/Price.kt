package com.rentme.rentme.model.filtermodel

import com.rentme.rentme.model.Type

data class Price(
    val id: Int,
    val quantity: Int,
    val type: Type
)