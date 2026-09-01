package com.ahj.onlineshop.feature.profile.presentation.userOrders

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class UserOrdersUiState(
    val status: UserOrdersStatus = UserOrdersStatus.EMPTY,
    val message: String? = null,
    val ordersData : List<UserOrderModel> = emptyList(),
    val header: HeaderDataModel? = null
)
