package com.ahj.onlineshop.feature.authentication.presentation.register

import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel

data class RegisterUiState(
    val registerModel: RegisterModel? = null,
    val registerStatus: RegisterStatus = RegisterStatus.IDLE,
    val stateEmail: String = "",
    val statePass: String = "",
    val statePassConfirm : String = "",
    val message : String? = null,
    val showDialog : Boolean = false

)