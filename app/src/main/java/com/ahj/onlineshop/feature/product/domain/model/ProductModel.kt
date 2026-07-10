package com.ahj.onlineshop.feature.product.domain.model

data class ProductModel(
    val id: String? = null,
    val title: String,
    val desc: String,
    val image: List<String>,
    val category: String,
    val price: Long,
    val discount: Int,
    val rating: String,
    val sales : Int = 0,
    val categoryType: String
) {
    val finalPrice: Long
        get() = if (discount > 0){
            price - (price * discount / 100)
        } else{
            price
        }
}
