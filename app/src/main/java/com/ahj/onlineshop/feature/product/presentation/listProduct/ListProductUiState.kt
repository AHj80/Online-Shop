package com.ahj.onlineshop.feature.product.presentation.listProduct

import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel

data class ListProductUiState(
    val status: ListProductStatus = ListProductStatus.IDLE,
    val messageStatus: String? = null,
    val message: String? = null,
    val product: List<ProductModel> = emptyList(),
    val subCategory: List<SubCategoryModel> = emptyList(),
    val category: List<CategoryModel> = emptyList(),
    val stateSearch: String = "",
    val selected: String = "",
    val parentCategory: String = ""
)