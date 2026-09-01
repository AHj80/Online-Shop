package com.ahj.onlineshop.core.sharedData.userProfile.data.mapper

import com.ahj.onlineshop.core.sharedData.userProfile.data.local.db.UserInformationEntity
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.ProfileDto
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.UserInformationDto
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ProfileModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel


fun ProfileDto.toDomain(): ProfileModel =
    ProfileModel(
        id = this.id,
        name = this.name ?: "",
        email = this.email,
        password = this.password,
        phone = this.phone ?: "",
        gender = this.gender
    )


fun ProfileModel.toDto(): ProfileDto =
    ProfileDto(
        id = this.id.toString(),
        name = this.name,
        phone = this.phone,
        email = this.email,
        password = this.password,
        gender = this.gender
    )

fun UserInformationDto.toEntity(): UserInformationEntity =
    UserInformationEntity(
        id = this.id.toInt(),
        name = this.name,
        phone = this.phone,
        email = this.email,
        gender = this.gender
    )

fun UserInformationEntity.toDomain(): UserInformationModel =
    UserInformationModel(
        this.id.toString(),
        this.name,
        this.email,
        this.phone,
        this.gender
    )

fun UserInformationDto.toDomain(): UserInformationModel =
    UserInformationModel(
        this.id,
        this.name,
        this.email,
        this.phone,
        this.gender
    )

fun UserInformationModel.toDto(): UserInformationDto =
    UserInformationDto(
        this.id ?: "",
        this.name,
        this.email,
        this.phone,
        this.gender
    )

fun UserInformationModel.toEntity(): UserInformationEntity =
    UserInformationEntity(
        id = this.id?.toInt() ?: 0,
        name = this.name,
        email = this.email,
        phone = this.phone,
        gender = this.gender
    )