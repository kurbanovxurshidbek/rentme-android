package com.rentme.rentme.model.base

data class BaseResponseList<T> (
    val data: List<T>,
    val success: Boolean,
    val totalCount: Int,
    val error: Error
)