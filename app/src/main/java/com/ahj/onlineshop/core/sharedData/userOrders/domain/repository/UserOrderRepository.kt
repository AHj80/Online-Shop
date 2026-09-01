package com.ahj.onlineshop.core.sharedData.userOrders.domain.repository

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import kotlinx.coroutines.flow.Flow


interface UserOrderRepository {

    suspend fun insertOrders(order: UserOrderModel):Result<Boolean>

    fun getAllOrders(): Flow<Result<List<UserOrderModel>>>
}