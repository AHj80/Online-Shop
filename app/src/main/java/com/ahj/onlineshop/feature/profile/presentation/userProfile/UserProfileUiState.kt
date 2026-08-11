package com.ahj.onlineshop.feature.profile.presentation.userProfile

import android.net.Uri
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel

data class UserProfileUiState(
    val status: UserProfileStatus = UserProfileStatus.IDLE,
    val profile: ProfileModel = ProfileModel.empty(),
    val message: String? = null,
    val avatar: Uri? = null
)
