package com.ahj.onlineshop.feature.product.domain.model

data class DetailProductData(
    val productData: ProductModel,
    val similarData: List<ProductModel>
)
