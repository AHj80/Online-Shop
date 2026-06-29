package com.ahj.onlineshop.feature.authentication.presentation.otp

data class OTPConfirmUiState(
    val status: OTPConfirmStatus = OTPConfirmStatus.IDLE,
    val result: Boolean = false,
    val message: String? = null,
    val stateOTP : String = "",
    val timer : Int = 0,
    val reSendCode : Boolean = true,
    val onDismiss : Boolean = false
)
