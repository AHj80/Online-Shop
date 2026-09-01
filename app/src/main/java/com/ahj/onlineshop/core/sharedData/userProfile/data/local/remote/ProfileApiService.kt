package com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote

import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.ChangePasswordDto
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.ProfileDto
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.dto.UserInformationDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path


interface ProfileApiService {
    @GET("user/{id}")
    suspend fun getProfileData(
        @Path("id") userId: String
    ): Response<ProfileDto>

    @PATCH("user/{id}")
    suspend fun changePassword(
        @Path("id")userId: String,
        @Body password: ChangePasswordDto
    ): Response<ChangePasswordDto>


    @GET("user/{id}")
    suspend fun getUserInformationData(
        @Path("id") userId: String
    ): Response<UserInformationDto>

    @PUT("user/{id}")
    suspend fun editedProfile(
        @Path("id") id: String,
        @Body editData: UserInformationDto
    ): Response<UserInformationDto>

}