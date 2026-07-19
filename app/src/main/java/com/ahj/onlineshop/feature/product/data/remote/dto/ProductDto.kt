package com.ahj.onlineshop.feature.product.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductDto(
    val id: String,
    val title: String,
    val desc: String,
    val image: List<String>,
    val category: String,
    val price: Long,
    val discount: Int,
    val rating: String,
    val categoryType: String,
    val sales: Int,
    val comments: List<CommentDto>,
    val features: List<String>

)
