package com.ahj.onlineshop.feature.profile.domain.repository

import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {

    suspend fun getProfileData(id: String): Result<ProfileModel>



}