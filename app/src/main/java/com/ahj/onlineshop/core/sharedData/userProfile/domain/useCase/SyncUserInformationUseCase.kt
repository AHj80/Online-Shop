package com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase

import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import javax.inject.Inject


class SyncUserInformationUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(id: String): Result<Boolean> = repository.syncData(id)
}