package com.rentme.rentme.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rentme.rentme.model.base.Error
import com.rentme.rentme.model.filtermodel.Advertisement

@Entity(tableName = "mainData")
data class MainPage(
    @PrimaryKey
    var id: Long? = null,
    val pictures: List<String>? = null,
    val brands: List<Brands>? = null,
    val lastAdvertisements: List<Advertisement>? = null,
    val dailyAdvertisements: List<Advertisement>? = null,
    val monthlyAdvertisements: List<Advertisement>? = null
)

data class TransportDTO (
    val model: Model? = null,
    val year: Long? = null,
    val transmission: String? = null,
    val fuelType: String? = null,
    val color: String? = null,
    val pictures: List<Picture>? = null,
    val wellEquipped: Boolean? = null
)

data class Picture (
    val path: String? = null,
    val main: Boolean? = null
)
