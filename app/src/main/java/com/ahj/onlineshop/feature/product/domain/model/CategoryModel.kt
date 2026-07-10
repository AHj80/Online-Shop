package com.ahj.onlineshop.feature.product.domain.model

import com.ahj.onlineshop.core.common.ui.component.productFeature.CategoryData

data class CategoryModel(
    override val id: Int,
    override val title: String,
    override val image: Int,
    override val categoryType: String
    ) : CategoryData
