package com.ahj.onlineshop.feature.product.domain.model

data class SelectedCategoryData(
    val id : Int,
    val subCategories: List<CategoryModel>,
    val product : List<ProductModel>
)
