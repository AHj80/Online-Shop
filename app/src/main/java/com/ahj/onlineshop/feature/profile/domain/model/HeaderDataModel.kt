package com.ahj.onlineshop.feature.profile.domain.model

import android.net.Uri


data class HeaderDataModel(
    val profile: ProfileModel,
    val avatar: Uri?,
    val userId: String
)