package com.ahj.onlineshop.feature.product.domain.model

data class ProductModel(
    val id: String,
    val title: String,
    val desc: String,
    val image: List<String>,
    val category: String,
    val price: Long,
    val discount: Int,
    val rating: String,
    val sales : Int = 0,
    val categoryType: String,
    val comments: List<CommentModel>,
    val features: List<String>
) {

    companion object{
        fun empty() = ProductModel(
            id = "",
            title = "نامشخص",
            desc = "",
            image = emptyList(),
            category = "",
            price = 0,
            discount = 0,
            rating = "0",
            sales = 0,
            categoryType = "",
            comments = emptyList(),
            features = emptyList()
        )
    }
    val finalPrice: Long
        get() = if (discount > 0){
            price - (price * discount / 100)
        } else{
            price
        }
}
