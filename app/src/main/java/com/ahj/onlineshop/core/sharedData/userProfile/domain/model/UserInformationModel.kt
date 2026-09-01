package com.ahj.onlineshop.core.sharedData.userProfile.domain.model




data class UserInformationModel(
    val id: String? = null,
    val name: String?,
    val email: String,
    val phone: String?,
    val gender: Boolean?,
){
    companion object{
        fun defaultValue(): UserInformationModel =
            UserInformationModel(
                id = null,
                "",
                "",
                "",
                null,
            )
    }
}

