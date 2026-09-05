package com.ahj.onlineshop.feature.product.presentation.detailProduct

import com.ahj.onlineshop.feature.product.domain.model.ProductModel

sealed interface DetailProductUiEvent {
    data class FavoriteOnClick(
        val isFavorite: Boolean,
        val productId: Int,
        val productTitle: String,
        val productImage: String,
        val productCategoryType: String
    ): DetailProductUiEvent

    data class OnTabChange(val index: Int) : DetailProductUiEvent
    data class OnSimilarClick(val id: String, val categoryType: String) : DetailProductUiEvent
    data class OnCommentChange(val text: String) : DetailProductUiEvent
    data class OnRateChange(val rate: Int) : DetailProductUiEvent
    data class OnSendComment(
        val id: Int,
        val title: String,
        val image: String,
        val text: String,
        val rate: Int
    ) : DetailProductUiEvent

    data class OnAddToCart(val product: ProductModel) : DetailProductUiEvent
}