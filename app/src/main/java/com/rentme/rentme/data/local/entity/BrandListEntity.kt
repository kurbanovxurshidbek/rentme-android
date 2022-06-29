package com.rentme.rentme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rentme.rentme.data.local.converter.ModelConverter
import com.rentme.rentme.model.Model

@Entity(tableName = "brand_list")
data class BrandListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val models: List<Model>
)