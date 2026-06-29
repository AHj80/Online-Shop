package com.ahj.onlineshop.feature.authentication.presentation.resetPassword



data class ResetPasswordUiState(
    val status : ResetPasswordStatus = ResetPasswordStatus.IDELE,
    val message: String? = null,
    val statePass: String = "",
    val statePassConfirm : String = "",
    val isError: Boolean = false
)
