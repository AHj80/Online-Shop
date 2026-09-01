package com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordDto(
    val password: String
)
