package com.ahj.onlineshop.core.sharedData.favorite.data.local.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProfileDto(
    val id: String,
    val name: String?,
    val lastName: String?,
    val email: String,
    val password: String,
    val phone: String?,
    val date: String?,
    val gender: String?,

)
