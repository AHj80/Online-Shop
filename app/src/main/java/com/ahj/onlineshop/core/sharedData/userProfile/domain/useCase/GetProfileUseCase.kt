package com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase

import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String) = repository.getProfileData(userId)
}