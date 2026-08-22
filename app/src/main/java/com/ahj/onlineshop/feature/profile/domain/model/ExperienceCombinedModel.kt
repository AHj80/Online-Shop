package com.ahj.onlineshop.feature.profile.domain.model

import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel

data class ExperienceCombinedModel(
    val header: HeaderDataModel,
    val experienceData: List<UserExperienceModel>
)