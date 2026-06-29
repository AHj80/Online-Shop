package com.ahj.onlineshop.feature.authentication.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val id: String = "",
    val email: String,
    val password : String,
)