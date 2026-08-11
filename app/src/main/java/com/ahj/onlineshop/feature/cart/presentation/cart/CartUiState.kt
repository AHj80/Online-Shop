package com.ahj.onlineshop.feature.cart.presentation.cart

import com.ahj.onlineshop.feature.cart.domain.model.CartModel

data class CartUiState(
    val cartStatus: CartStatus = CartStatus.EMPTY,
    val message: String? = null,
    val data: List<CartModel> = emptyList(),
    val price: Long = 0,
    val finalPrice: Long = 0,
    val discount: Long = 0
)