package com.rentme.rentme.model.base

data class BaseResponse<T> (
    val data : T,
    var status: Int = 0
)