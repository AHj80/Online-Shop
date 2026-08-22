package com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model



data class UserExperienceModel(
    val id: Int,
    val title: String,
    val image: String,
    val comment: String,
    val rate: Int
)
