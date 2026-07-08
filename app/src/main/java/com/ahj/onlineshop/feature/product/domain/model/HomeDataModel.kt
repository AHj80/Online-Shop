package com.ahj.onlineshop.feature.product.domain.model


data class HomeDataModel(
    val product: List<ProductModel>,
    val banner: List<BannerModel>,
    val category: List<CategoryModel>
)