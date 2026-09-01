package com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProfileDto(
    val id: String,
    val name: String?,
    val email: String,
    val password: String,
    val phone: String?,
    val gender: Boolean?,
)
