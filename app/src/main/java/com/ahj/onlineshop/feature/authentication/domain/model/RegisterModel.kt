package com.ahj.onlineshop.feature.authentication.domain.model

data class RegisterModel(
    val id: String? = null,
    val email: String,
    val password: String,
    val username : String = "",
)
