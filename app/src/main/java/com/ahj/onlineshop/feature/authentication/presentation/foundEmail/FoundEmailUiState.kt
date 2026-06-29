package com.ahj.onlineshop.feature.authentication.presentation.foundEmail

import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel

data class FoundEmailUiState(
    val foundEmailStatus: FoundEmailStatus = FoundEmailStatus.IDLE,
    val message: String? = null,
    val loginModel: LoginModel? = null,
    val stateEmail: String = "",
    val isError: Boolean = false,
    val showDialog: Boolean = false
)
