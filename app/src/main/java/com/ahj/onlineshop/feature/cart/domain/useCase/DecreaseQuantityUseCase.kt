package com.ahj.onlineshop.feature.cart.domain.useCase

import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import javax.inject.Inject


class DecreaseQuantityUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(
        id: String,
        currentQuantity: Int,
        currentProduct: CartModel
    ){
        if (currentQuantity>1)
            repository.decreaseQuantity(id)
        else
            repository.deleteProduct(currentProduct)
    }
}