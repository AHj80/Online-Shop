package com.ahj.onlineshop.feature.profile.domain.useCase

import androidx.core.net.toUri
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.core.sharedData.favorite.domain.repository.FavoriteRepository
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel
import com.ahj.onlineshop.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetHeaderDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val sessionManager: SessionManager
) {
    operator fun invoke(): Flow<Result<HeaderDataModel>> {

        return combine(
            sessionManager.loginUser,
            sessionManager.setAvatarProfile
        ) { userId, avatar ->

            val profile = profileRepository.getProfileData(userId ?: "").getOrThrow()

            HeaderDataModel(
                profile,
                avatar?.toUri(),
                userId ?: ""
            )
        }.map { header ->
            Result.success(header)
        }.catch { error ->
            emit(Result.failure(error))
        }
    }


}