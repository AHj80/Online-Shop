package com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.useCase

import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.repository.UserExperienceRepository
import javax.inject.Inject

class GetUserExperienceUseCase @Inject constructor(
    private val repository: UserExperienceRepository
) {
    operator fun invoke() =
        repository.getExperience()
}