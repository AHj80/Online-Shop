package com.ahj.onlineshop.feature.product.domain.model

data class AddCartModel(
    val id: String = "",
    val title: String,
    val image: String,
    val quantity: Int,
    val price: Long,
    val finalPrice: Long,
    val discount: Int
)
