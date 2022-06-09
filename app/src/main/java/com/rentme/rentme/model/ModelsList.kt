package com.rentme.rentme.model

data class ModelsList (
    val data: Data? = null,
    val status: Long? = null
)

data class Data (
    val data: List<String>? = null,
    val success: Boolean? = null,
    val totalCount: Long? = null
)

