package com.rentme.rentme.model.filtermodel

data class Transport(
    val color: String,
    val fuelType: String,
    val model: Model,
    val pictures: List<Picture>,
    val transmission: String,
    val wellEquipped: Boolean,
    val year: Int
)