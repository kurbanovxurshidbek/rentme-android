package com.rentme.rentme.model

data class FilterPage (
    var size: Long? = null,
    var page: Long? = null,
    var location: Location? = null,
    var category: String? = null,
    var model: String? = null,
    var year: Int? = null,
    var price: PriceFilter? = null,
    var date: Date? = null,
    var colors: List<String>? = null
)

data class PriceFilter(
    var type: String,
    var minPrice: Int,
    var maxPrice: Int
)

data class Date(
    var startData : Long? = null,
    var endData : Long? = null
)