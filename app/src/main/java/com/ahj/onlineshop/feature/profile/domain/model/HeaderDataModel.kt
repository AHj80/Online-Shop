package com.ahj.onlineshop.feature.profile.domain.model

import android.net.Uri
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ProfileModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel


data class HeaderDataModel(
    val profile: UserInformationModel?,
    val avatar: Uri?,
    val userId: String
)