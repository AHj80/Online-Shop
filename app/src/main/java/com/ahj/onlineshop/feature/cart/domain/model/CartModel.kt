package com.ahj.onlineshop.feature.cart.domain.model

data class CartModel(
    val id: String ,
    val title: String ,
    val image: String,
    val quantity: Int,
    val price: Long,
    val finalPrice: Long,
    val discount: Int,
    val categoryType: String

){
    fun empty(): CartModel =
        CartModel(
            "0",
            "",
            "",
            1,
            0,
            0,
            0,
            categoryType = ""
        )
}
