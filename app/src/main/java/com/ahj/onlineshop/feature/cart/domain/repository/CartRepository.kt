package com.ahj.onlineshop.feature.cart.domain.repository

import com.ahj.onlineshop.feature.cart.domain.model.CartModel

interface CartRepository {

    suspend fun increaseQuantity(id: String):Result<Boolean>

    suspend fun decreaseQuantity(id: String):Result<Boolean>

    suspend fun deleteProduct(product: CartModel): Result<Boolean>

    suspend fun deleteAllRecord(): Result<Boolean>

}
