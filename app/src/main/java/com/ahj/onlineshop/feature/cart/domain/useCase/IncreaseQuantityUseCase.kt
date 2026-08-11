package com.ahj.onlineshop.feature.cart.domain.useCase

import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import javax.inject.Inject


class IncreaseQuantityUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(id: String) = repository.increaseQuantity(id)


}