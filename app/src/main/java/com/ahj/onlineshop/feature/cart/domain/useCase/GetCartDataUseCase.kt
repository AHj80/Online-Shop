package com.ahj.onlineshop.feature.cart.domain.useCase

import com.ahj.onlineshop.feature.cart.domain.model.CartCalculation
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartDataUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<Result<CartCalculation>> =
        cartRepository.getCartData().map { result ->
            result.map { list ->

                val price = list.sumOf { item -> item.price * item.quantity }
                val discount =
                    list.sumOf { item -> ((item.price * item.discount) / 100) * item.quantity }

                val finalPrice = (price - discount) + 100000L

                CartCalculation(
                    list,
                    price,
                    discount,
                    finalPrice
                )
            }
        }
}