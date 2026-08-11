package com.ahj.onlineshop.feature.profile.data.repository

import com.ahj.onlineshop.core.common.utils.ApiHelper
import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.feature.profile.data.mapper.toModel
import com.ahj.onlineshop.core.common.sharedData.profile.data.local.remote.ProfileApiService
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel
import com.ahj.onlineshop.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService,
    private val addressDao: AddressDao,
    private val apiHelper: ApiHelper,
    private val runCatching: RunCatching
) : ProfileRepository {



    override suspend fun getProfileData(id: String): Result<ProfileModel> =
        apiHelper.safeData {
            profileApiService.getProfileData(userId = id)
        }.map {
            it?.toModel()?: ProfileModel.empty()
        }




}