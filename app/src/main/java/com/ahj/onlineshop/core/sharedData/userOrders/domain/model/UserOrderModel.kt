package com.ahj.onlineshop.core.sharedData.userOrders.domain.model


data class UserOrderModel(
    val id: Long? = null,
    val date: String,
    val orderResult: Boolean,
    val orderCode: String,
    val orderPrice: String,
    val item : List<OrderItemModel>
)
