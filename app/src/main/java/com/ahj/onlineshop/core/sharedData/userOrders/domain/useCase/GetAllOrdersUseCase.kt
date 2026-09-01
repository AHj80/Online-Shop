package com.ahj.onlineshop.core.sharedData.userOrders.domain.useCase

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderDataModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.repository.UserOrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllOrdersUseCase @Inject constructor(
    private val repository: UserOrderRepository
) {
    operator fun invoke(): Flow<Result<List<UserOrderModel>>> = repository.getAllOrders()
}