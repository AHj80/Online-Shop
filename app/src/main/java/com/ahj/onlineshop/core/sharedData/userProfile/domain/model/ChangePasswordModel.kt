package com.ahj.onlineshop.core.sharedData.userProfile.domain.model


data class ChangePasswordModel(
    val newPassword: String
) {
    companion object {
        fun defaultValue() = ChangePasswordModel("")
    }
}
