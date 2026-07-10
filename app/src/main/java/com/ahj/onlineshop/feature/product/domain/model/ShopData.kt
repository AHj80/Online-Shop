package com.ahj.onlineshop.feature.product.domain.model



data class ShopData(
    val categories : List<CategoryModel> = emptyList(),
    val subCategories: List<SubCategoryModel> = emptyList(),
    val product: List<ProductModel> = emptyList()
)