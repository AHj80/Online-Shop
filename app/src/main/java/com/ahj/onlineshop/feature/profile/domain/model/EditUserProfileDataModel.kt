package com.ahj.onlineshop.feature.profile.domain.model

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel


data class EditUserProfileDataModel(
    val header: HeaderDataModel,
    val editData: UserInformationModel?
)
