package com.ahj.onlineshop.feature.profile.domain.useCase

import androidx.core.net.toUri
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetHeaderDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<Result<HeaderDataModel>> {

        return sessionManager.loginUser.flatMapLatest { useId ->

            combine(
                sessionManager.loginUser,
                sessionManager.setAvatarProfile,
                profileRepository.getUserInformationFlow(useId ?: "")
            ) { userId, avatar, profile ->

                val id = if (avatar.isNullOrBlank()) null else avatar
                HeaderDataModel(
                    profile.getOrThrow(),
                    id?.toUri(),
                    userId ?: ""
                )
            }.map { header ->
                Result.success(header)
            }.catch { error ->
                emit(Result.failure(error))
            }
        }

    }


}