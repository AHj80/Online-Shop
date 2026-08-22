package com.ahj.onlineshop.feature.product.presentation.detailProduct

import com.ahj.onlineshop.feature.product.domain.model.ProductModel


data class DetailProductUiState(
    val status: DetailProductStatus = DetailProductStatus.IDLE,
    val product: ProductModel = ProductModel.empty(),
    val similarProduct : List<ProductModel> = emptyList(),
    val message: String? = null,
    val selectedTab: Int = 0,
    val quantity: Int = 1,
    val inCart : Boolean = true,
    val isFavorite: Boolean = false,
    val stateTextComment: String = "",
    val rate: Int = 0

)