package com.ahj.onlineshop.app.base

import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel

data class MainUiState(
    val data: HeaderDataModel? = null,
    val message: String? = null
)
