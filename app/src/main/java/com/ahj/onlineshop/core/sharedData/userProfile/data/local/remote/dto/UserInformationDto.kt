package com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserInformationDto(
    val id: String,
    val name: String?,
    val email: String,
    val phone: String?,
    val gender: Boolean?,
)