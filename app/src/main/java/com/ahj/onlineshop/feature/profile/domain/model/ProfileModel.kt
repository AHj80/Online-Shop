package com.ahj.onlineshop.feature.profile.domain.model

import kotlin.String

data class ProfileModel(
    val id: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val date: String,
    val gender: String
) {
    companion object {
        fun defaultValue() =
            ProfileModel(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
    }
}
