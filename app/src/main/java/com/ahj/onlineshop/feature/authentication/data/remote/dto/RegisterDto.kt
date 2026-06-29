package com.ahj.onlineshop.feature.authentication.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class RegisterDto(
    val id: String? = null,
    val email: String,
    val password: String,
    val username: String
)
