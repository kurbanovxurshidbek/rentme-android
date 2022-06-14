package com.rentme.rentme.model.base

data class Error(
    val timestamp: String? = null,
    val status: Long? = null,
    val error: String? = null,
    val message: String,
    val path: String? = null
)