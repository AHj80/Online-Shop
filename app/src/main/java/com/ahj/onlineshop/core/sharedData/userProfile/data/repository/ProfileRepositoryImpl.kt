package com.ahj.onlineshop.core.sharedData.userProfile.data.repository

import com.ahj.onlineshop.core.common.utils.ApiHelper
import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.db.UserInformationDao
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.ProfileApiService
import com.ahj.onlineshop.core.sharedData.userProfile.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.userProfile.data.mapper.toDto
import com.ahj.onlineshop.core.sharedData.userProfile.data.mapper.toEntity
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ChangePasswordModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ProfileModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import com.ahj.onlineshop.feature.profile.data.mapper.toDomain
import com.ahj.onlineshop.feature.profile.data.mapper.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService,
    private val apiHelper: ApiHelper,
    private val runCatching: RunCatching,
    private val dao: UserInformationDao
) : ProfileRepository {


    override suspend fun getProfileData(id: String): Result<ProfileModel> =
        apiHelper.safeData {

            profileApiService.getProfileData(userId = id)
        }.map {
            it?.toDomain() ?: ProfileModel.defaultValue()
        }

    override suspend fun getUserInformationData(id: String): Result<UserInformationModel> =
        apiHelper.safeData {

            profileApiService.getUserInformationData(id)
        }.map { it?.toDomain() ?: UserInformationModel.defaultValue() }


    override suspend fun changePassword(
        id: String,
        newPass: ChangePasswordModel
    ): Result<ChangePasswordModel> =
        apiHelper.safeData {
            profileApiService.changePassword(
                id, newPass.toDto()
            )
        }.map {
            it?.toDomain() ?: ChangePasswordModel.defaultValue()
        }

    override suspend fun editedUserInformationApi(
        id: String,
        editProfileModel: UserInformationModel
    ): Result<UserInformationModel> =

        apiHelper.safeData {
            profileApiService.editedProfile(id, editProfileModel.toDto())
        }.map { data ->
            data?.toDomain() ?: UserInformationModel.defaultValue()
        }

    override suspend fun insertUserInformation(profile: UserInformationModel): Result<Boolean> =
        runCatching.safeData {
            dao.insertUserInformation(profile.toEntity())
            true
        }

    override suspend fun syncData(id: String): Result<Boolean> {
        val networkData = getUserInformationData(id).getOrElse { return Result.failure(it) }

        val insertData = insertUserInformation(networkData).fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )

        return insertData
    }

    override fun getUserInformationFlow(id: String): Flow<Result<UserInformationModel?>> =
        flow {
            dao.getUserInformation()
                .onStart {
                    if (dao.getUserInformationFirst() == null) {
                        apiHelper.safeData {
                            profileApiService.getUserInformationData(id)
                        }.onSuccess {
                            it?.let { dto ->
                                dao.insertUserInformation(dto.toEntity())
                            }
                        }
                    }
                }
                .collect { data ->
                    emit(
                        runCatching.safeData {
                            data?.toDomain()
                        }

                    )
                }
        }


    override suspend fun updateProfile(editedData: UserInformationModel): Result<Boolean> =
        runCatching.safeData {
            dao.saveEditedData(editedData.toEntity())
            true
        }


}

