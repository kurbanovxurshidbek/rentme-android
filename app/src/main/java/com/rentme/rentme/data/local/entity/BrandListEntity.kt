package com.rentme.rentme.data.local.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BrandListEntity::class,
            parentColumns = ["id"],
            childColumns = ["brandRefFK"]
        )
    ]
)
data class ModelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val category: String,
    val imagePath: String,
    @Nullable val brandRefFK: Int
)

@VersionedParcelize
@Entity(tableName = "brand_list")
data class BrandListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val image: String,
    val name: String,
    @Ignore
    val models: List<ModelEntity>
)