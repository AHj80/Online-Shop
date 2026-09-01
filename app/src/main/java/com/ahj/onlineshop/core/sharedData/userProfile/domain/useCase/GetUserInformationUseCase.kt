package com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke(id: String): Flow<Result<UserInformationModel?>> = repository.getUserInformationFlow(id)

}