package com.rentme.rentme.model

data class FileResponse(
    val data: FileUrl,
    val status: Int
)

data class FileUrl(
    val data: ArrayList<String>,
    val success: Boolean,
    val totalCount: Int,
)
