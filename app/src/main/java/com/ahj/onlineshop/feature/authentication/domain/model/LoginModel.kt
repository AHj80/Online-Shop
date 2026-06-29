package com.ahj.onlineshop.feature.authentication.domain.model

data class LoginModel(
    val id: String = "",
    val email : String,
    val password: String,
)