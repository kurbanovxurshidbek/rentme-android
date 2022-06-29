package com.rentme.rentme.model.filtermodel

import com.rentme.rentme.model.FuelType
import com.rentme.rentme.model.Transmission

data class Transport(
    val id:Int? = null,
    val color: String,
    val fuelType: FuelType,
    val model: Model,
    val pictures: List<Picture>,
    val transmission: Transmission,
    val wellEquipped: Boolean,
    val year: Int
)