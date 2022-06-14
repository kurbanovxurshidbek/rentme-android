package com.rentme.rentme.model.base

data class BaseResponse<T> (
    val data : T,
    val status: Int? = null
)