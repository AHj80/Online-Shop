package com.ahj.onlineshop.feature.cart.domain.useCase

import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.repository.UserOrderRepository
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import javax.inject.Inject


class PaymentProcessUseCase @Inject constructor(
    private val orderRepository: UserOrderRepository,
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(order: UserOrderModel): Result<Unit> {
        if (!order.orderResult) {
            orderRepository.insertOrders(order)
            return Result.failure(Exception("عملیات پرداخت ناموفق بود ، لطفا مجددا اقدام نمایید(در صورت کسر هزینه پس از 48 ساعت کاری عودت داده خواهد شد)"))
        }

        return orderRepository.insertOrders(order).fold(
            onSuccess = {
                cartRepository.deleteAllRecord().map { Unit }

            },
            onFailure = { error ->
                Result.failure(error)
            }
        )

    }


}