package com.ahj.onlineshop.feature.profile.domain.model

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel


data class OrdersDataModel(
    val order: List<UserOrderModel>,
    val header: HeaderDataModel
)