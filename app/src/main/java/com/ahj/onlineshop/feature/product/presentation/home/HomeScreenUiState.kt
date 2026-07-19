package com.ahj.onlineshop.feature.product.presentation.home

import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel


data class HomeScreenUiState(
    val status: HomeScreenStatus = HomeScreenStatus.IDLE,
    val data: List<ProductModel> = emptyList(),
    val message: String? = null,
    val stateSearch: String = "",
    val categories: List<CategoryModel> = emptyList(),
    val banner: List<BannerModel> = emptyList(),
    val showModal: Boolean = false
)
