package com.ahj.onlineshop.feature.profile.data.mapper

import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.ChangePasswordDto
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ChangePasswordModel


fun ChangePasswordModel.toDto(): ChangePasswordDto =
    ChangePasswordDto(
        password = this.newPassword
    )

fun ChangePasswordDto.toDomain(): ChangePasswordModel =
    ChangePasswordModel(
        newPassword = this.password
    )

