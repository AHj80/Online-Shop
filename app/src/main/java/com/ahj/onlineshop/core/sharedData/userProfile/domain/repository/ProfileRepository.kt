package com.ahj.onlineshop.core.sharedData.userProfile.domain.repository

import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ChangePasswordModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.ProfileModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfileData(id: String): Result<ProfileModel>

    suspend fun getUserInformationData(id: String): Result<UserInformationModel>

    suspend fun changePassword(id: String,newPass: ChangePasswordModel): Result<ChangePasswordModel>

    suspend fun editedUserInformationApi(id: String, editProfileModel: UserInformationModel): Result<UserInformationModel>

    suspend fun insertUserInformation(profile: UserInformationModel): Result<Boolean>

     fun getUserInformationFlow(id: String): Flow<Result<UserInformationModel?>>

    suspend fun syncData(id: String): Result<Boolean>

    suspend fun updateProfile(editedData: UserInformationModel) : Result<Boolean>


}