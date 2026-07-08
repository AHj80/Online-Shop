package com.ahj.onlineshop.feature.product.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductDto(
    val id: String? = null,
    val title: String,
    val desc: String,
    val image: List<String>,
    val category: String,
    val price: Long,
    val discount: Int,
    val rating: String,
    val categoryType: String
)
