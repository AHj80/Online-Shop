package com.ahj.onlineshop.feature.profile.domain.useCase

import com.ahj.onlineshop.feature.profile.data.repository.ProfileRepositoryImpl
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val repository: ProfileRepositoryImpl
) {
    suspend operator fun invoke(id: String): Result<ProfileModel> =
        repository.getProfileData(id).fold(
            onSuccess = { data ->
                Result.success(data)
            },
            onFailure = { error ->
                Result.failure(Exception(error))
            }
        )

}