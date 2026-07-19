package com.ahj.onlineshop.feature.authentication.presentation.login

import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel


data class LoginUiState(
    val loginStatus: LoginStatus = LoginStatus.IDLE,
    val loginModel: LoginModel? = null,
    val stateEmail : String = "",
    val statePass : String = "",
    val isError : Boolean = false,
    val message : String? = null,
    val enable : Boolean = false,
    val showAlertDialog : Boolean = false
)