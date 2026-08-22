package com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.useCase

import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.repository.UserExperienceRepository
import javax.inject.Inject


class AddExperienceUseCase @Inject constructor(
    private val repository: UserExperienceRepository
) {

    suspend operator fun invoke(experience: UserExperienceModel): Result<Boolean> =
        repository.addExperience(experience)

}