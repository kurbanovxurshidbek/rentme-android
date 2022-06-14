package com.rentme.rentme.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rentme.rentme.model.base.Error
import com.rentme.rentme.model.filtermodel.Advertisement

//data class MainPage(
//    val data: WelcomeData? = null,
//    val status: Long? = null
//)
//

@Entity(tableName = "mainData")
data class WelcomeData(
    @PrimaryKey
    val id: Long? = null,
    val data: MainPage? = null,
    val error: Error? = null,
    val success: Boolean? = null,
    val totalCount: Long? = null
)

data class MainPage(
    val picturePathList: List<String>? = null,
    val lastAdvertisements: List<Advertisement>? = null,
    val dailyAdvertisements: List<Advertisement>? = null,
    val weeklyAdvertisements: List<Advertisement>? = null
)

//data class Advertisement (
//    val description: String? = null,
//    val prices: List<PriceDTO>? = null,
//    val category: String? = null,
//    val transport: TransportDTO? = null
//)

data class PriceDTO (
    val quantity: Long? = null,
    val type: String? = null
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

data class ModelDTO (
    val name: String? = null,
    val category: String? = null,
    val imagePath: String? = null
)

data class Picture (
    val path: String? = null,
    val main: Boolean? = null
)
