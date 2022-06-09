package com.rentme.rentme.model

data class FileResponse(
    val fileUrl: FileUrl? = null,
    val status: Int? = null
)

data class FileUrl(
    val urls: ArrayList<String>? = null,
    val success: Boolean? = null,
    val totalCount: Int? = null,
)
