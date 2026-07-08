package com.ahj.onlineshop.feature.product.domain.model

import com.ahj.onlineshop.core.common.ui.component.productFeature.CategoryData

data class SubCategoryModel(
    override val id: Int,
    override val title: String,
    val parentCategory: String,
    override val image : Int
): CategoryData
