package com.ahj.onlineshop.core.sharedData.shoppingExperience.data.repository

import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db.UserExperienceDao
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.mapper.toEntity
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.repository.UserExperienceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.exp


class UserExperienceRepositoryImpl @Inject constructor(
    private val runCatching: RunCatching,
    private val dao: UserExperienceDao
) : UserExperienceRepository {
    override suspend fun addExperience(experience: UserExperienceModel): Result<Boolean> =
        runCatching.safeData {
            dao.addExperience(experience.toEntity()) > 0
        }


    override fun getExperience(): Flow<Result<List<UserExperienceModel>>> =
        dao.getExperience().map { data ->
            runCatching.safeData {
                data.map { experience ->
                    experience.toDomain()
                }
            }
        }

}