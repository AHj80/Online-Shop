package com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.repository

import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import kotlinx.coroutines.flow.Flow

interface UserExperienceRepository {
    suspend fun addExperience(experience: UserExperienceModel): Result<Boolean>

    fun getExperience(): Flow<Result<List<UserExperienceModel>>>
}