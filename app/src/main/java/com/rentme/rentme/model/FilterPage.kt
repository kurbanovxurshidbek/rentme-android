package com.rentme.rentme.model

data class FilterPage (
    var color :List<String>? = null,
    var model : String? = null,
    var price : PriceFilter? = null,
    var date: Date? = null,
    var year : Int? = null
)

data class PriceFilter(
    var type: String,
    var minPrice: Int,
    var maxPrice: Int
)

data class Date(
    var startData : Int? = null,
    var endData : Int? = null
)