package com.ahj.onlineshop.feature.cart.domain.model

data class CartCalculation(
    val item: List<CartModel>,
    val cartPrice: Long = 0,
    val cartDiscount: Long = 0,
    val cartFinalPrice: Long = 0,
    val sendingCost: Long = 100000
){
    companion object{
        fun defaultValue(): CartCalculation =
            CartCalculation(
                emptyList(),
                0,
                0,
                0,
                0
            )
    }
}
