package com.ahj.onlineshop.core.sharedData.product.domain.useCase

import com.ahj.onlineshop.core.sharedData.product.domain.repository.ProductCartRepository
import com.ahj.onlineshop.feature.cart.domain.model.CartCalculation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartDataUseCase @Inject constructor(
    private val productCartRepository: ProductCartRepository
) {
    operator fun invoke(): Flow<Result<CartCalculation>> =
        productCartRepository.getCartData().map { result ->
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