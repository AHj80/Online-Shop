package com.ahj.onlineshop.feature.product.presentation.category

import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel

data class CategoryUiState(
    val status: CategoryScreenStatus = CategoryScreenStatus.IDLE,
    val category: List<CategoryModel> = emptyList(),
    val banner: List<BannerModel> = emptyList(),
    val message: String? =  null,
    val selectedCategory: Int = 0
)
