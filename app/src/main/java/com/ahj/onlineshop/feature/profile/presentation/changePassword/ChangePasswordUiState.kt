package com.ahj.onlineshop.feature.profile.presentation.changePassword

import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class ChangePasswordUiState(
    val status: ChangePasswordStatus = ChangePasswordStatus.IDELE,
    val message: String? = null,
    val header: HeaderDataModel? = null,
    val oldPass: String = "",
    val newPass: String = "",
    val confirmPass: String = "",
    val isError: Boolean = false,
    val visibleEye: Boolean = true,
    val loading: Boolean = false,
    val alertDialog: Boolean = false
)