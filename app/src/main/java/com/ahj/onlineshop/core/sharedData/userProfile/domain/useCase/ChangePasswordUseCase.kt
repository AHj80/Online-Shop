package com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ChangePasswordModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        id: String,
        oldPass: String,
        newPassword: String
    ): Result<Unit> {

        val profileResult = repository.getProfileData(id).getOrElse { error->
            return Result.failure(error)
        }

        if (profileResult.password != oldPass){
            return Result.failure(Exception("رمز عبور فعلی نادرست میباشد"))
        }

        val newPass = ChangePasswordModel(newPassword)
        return repository.changePassword(id , newPass).map {
            Unit
        }

    }
}