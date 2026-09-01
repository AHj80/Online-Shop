package com.ahj.onlineshop.core.sharedData.userProfile.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserInformationEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String?,
    val phone: String?,
    val email: String,
    val gender: Boolean?
)
