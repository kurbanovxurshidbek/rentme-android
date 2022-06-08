package com.rentme.rentme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model_list")
data class ModelsListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var modelName: String
)