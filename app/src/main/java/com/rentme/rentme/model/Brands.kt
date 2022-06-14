package com.rentme.rentme.model

data class Brands(
    val id: Long? = null,
    val image: String? = null,
    val name: String? = null,
    val models: List<Model>? = null
)

data class Model(
    val id: Long? = null,
    val name: String? = null,
    val category: String? = null,
    val imagePath: String? = null
)
