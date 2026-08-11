package com.ahj.onlineshop.feature.profile.data.mapper

import com.ahj.onlineshop.core.common.sharedData.profile.data.local.remote.dto.ProfileDto
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel


fun ProfileDto.toModel(): ProfileModel =
    ProfileModel(
        id = this.id,
        name = this.name ?: "",
        lastName = this.lastName ?: "",
        email = this.email,
        password = this.password,
        phone = this.phone ?: "",
        date = this.date ?: "",
        gender = this.gender ?: ""
    )
