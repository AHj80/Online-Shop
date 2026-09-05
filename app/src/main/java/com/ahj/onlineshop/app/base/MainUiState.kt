package com.ahj.onlineshop.app.base

import androidx.compose.material3.SnackbarHostState
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel

data class MainUiState(
    val data: HeaderDataModel? = null,
    val message: String? = null,
    val stateSnackBar: SnackbarHostState = SnackbarHostState()
)
