package com.rentme.rentme.model.base

data class BaseResponseObject<T> (
    val data: T,
    val success: Boolean? = null,
    val totalCount: Int? = null,
    val error: Error? = null
)