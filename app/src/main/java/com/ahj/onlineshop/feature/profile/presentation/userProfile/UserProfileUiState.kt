package com.ahj.onlineshop.feature.profile.presentation.userProfile

import android.net.Uri
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ProfileModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel

data class UserProfileUiState(
    val status: UserProfileStatus = UserProfileStatus.IDLE,
    val profile: UserInformationModel? = null,
    val message: String? = null,
    val avatar: Uri? = null,
    val userId: String =""
)
