package com.ahj.onlineshop.feature.authentication.presentation.splash


data class SplashUiState(
    val result : Boolean = false,
    val message : String? = null,
    val statusSplash: StatusSplash = StatusSplash.LOADING
)
