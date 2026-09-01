package com.ahj.onlineshop.core.sharedData.userProfile.domain.model

data class ProfileModel(
    val id: String?,
    val name: String?,
    val email: String,
    val password: String,
    val phone: String?,
    val gender: Boolean?,
) {
    companion object {
        fun defaultValue() =
            ProfileModel(
                null,
                "",
                "",
                "",
                "",
                null,
            )
    }
}