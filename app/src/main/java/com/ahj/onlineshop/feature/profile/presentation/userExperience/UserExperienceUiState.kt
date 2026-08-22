package com.ahj.onlineshop.feature.profile.presentation.userExperience

import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class UserExperienceUiState(
    val status: UserExperienceStatus = UserExperienceStatus.EMPTY,
    val experienceData: List<UserExperienceModel> = emptyList(),
    val header: HeaderDataModel? = null,
    val message: String? = null
)