package com.rentme.rentme.model

import java.io.Serializable

data class Location(
    var name: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
): Serializable {
}