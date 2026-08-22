package com.ahj.onlineshop.core.sharedData.shoppingExperience.data.mapper

import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db.UserExperienceEntity
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel


fun UserExperienceModel.toEntity(): UserExperienceEntity =
    UserExperienceEntity(
        this.id,
        this.title,
        this.image,
        this.comment,
        this.rate
    )

fun UserExperienceEntity.toDomain(): UserExperienceModel =
    UserExperienceModel(
        this.id,
        this.title,
        this.image,
        this.comment,
        this.rate
    )