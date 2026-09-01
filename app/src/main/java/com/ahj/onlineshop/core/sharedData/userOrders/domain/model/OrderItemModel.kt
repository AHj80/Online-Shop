package com.ahj.onlineshop.core.sharedData.userOrders.domain.model


data class OrderItemModel(
    val id: Long? = null,
    val orderId: Long = 0,
    val productId: String,
    val image: String,
    val title: String,
    val quantity: Int,
    val categoryType: String
)
