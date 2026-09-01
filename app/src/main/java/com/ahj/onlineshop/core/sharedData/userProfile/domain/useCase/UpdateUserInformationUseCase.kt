package com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserInformationUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(id: String, editProfile: UserInformationModel): Result<Boolean> {
        val apiResult = repository.editedUserInformationApi(id, editProfile)

        val newData = apiResult.getOrElse { return Result.failure(it) }

        return repository.updateProfile(newData)

    }

}