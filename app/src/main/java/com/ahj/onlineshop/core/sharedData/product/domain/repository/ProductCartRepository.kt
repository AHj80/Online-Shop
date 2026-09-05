package com.ahj.onlineshop.core.sharedData.product.domain.repository

import com.ahj.onlineshop.core.sharedData.product.domain.model.AddCartModel
import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductCartRepository {

    suspend fun addProductToCart(product: ProductModel): Result<Long>

    fun getCartDataById(id: String): Flow<AddCartModel?>

    fun getCartData(): Flow<Result<List<CartModel>>>
}