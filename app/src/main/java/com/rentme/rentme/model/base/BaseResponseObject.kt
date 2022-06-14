package com.rentme.rentme.model.base

data class BaseResponseObject<T> (
    val data: T,
    val success: Boolean,
    val totalCount: Int,
    val error: Error
)