package com.ahj.onlineshop.core.sharedData.userOrders.domain.model

data class UserOrderDataModel(
    val userOrderModel: List<UserOrderModel>,
    val orderItemModel: List<OrderItemModel>
)
