package com.ahj.onlineshop.feature.profile.presentation.editProfile

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class EditUserProfileUiState(
    val status: EditUserProfileStatus = EditUserProfileStatus.IDELE,
    val message: String? = null,
    val header: HeaderDataModel? = null,
    val profile: UserInformationModel? = null,
    val stateName: String = "",
    val statePhone: String = "",
    val stateEmail: String = "",
    val gender: Boolean = false,
    val isLoading: Boolean = false,
    val alertDialog : Boolean = false

    )
