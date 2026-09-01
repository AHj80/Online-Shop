package com.ahj.onlineshop.core.sharedData.userOrders.domain.useCase

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.repository.UserOrderRepository
import javax.inject.Inject


class AddOrderUseCase @Inject constructor(
    private val repository: UserOrderRepository
) {


    suspend operator fun invoke(
        order: UserOrderModel
    ): Result<Boolean> = repository.insertOrders(order)

}