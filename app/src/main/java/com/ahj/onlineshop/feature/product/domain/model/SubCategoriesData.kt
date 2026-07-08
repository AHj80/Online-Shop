package com.ahj.onlineshop.feature.product.domain.model



data class SubCategoriesData(
    val categories : List<CategoryModel> = emptyList(),
    val subCategories: List<SubCategoryModel> = emptyList(),
    val bestSales: List<ProductModel> = emptyList()
)