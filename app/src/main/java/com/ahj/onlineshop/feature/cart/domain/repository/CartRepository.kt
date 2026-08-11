package com.ahj.onlineshop.feature.cart.domain.repository

import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartData(): Flow<Result<List<CartModel>>>

    suspend fun increaseQuantity(id: String):Result<Boolean>

    suspend fun decreaseQuantity(id: String):Result<Boolean>

    suspend fun deleteProduct(product: CartModel): Result<Boolean>

}
