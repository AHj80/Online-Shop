package com.ahj.onlineshop.feature.product.presentation.subCategory

import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel

data class SubCategoriesUiState(
    val status : SubCategoriesStatus = SubCategoriesStatus.IDLE,
    val categories : List<CategoryModel> = emptyList(),
    val product: List<ProductModel> = emptyList(),
    val subCategories : List<SubCategoryModel> = emptyList(),
    val messageStatus : String? = null,
    val message : String? = null,
    val selected: String = "",
    val stateText : String = "",
    val showModal: Boolean = false

)
