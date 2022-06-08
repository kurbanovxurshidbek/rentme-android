package com.rentme.rentme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models_history")
data class ModelsHistory(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String)
