package com.ahj.onlineshop.core.sharedData.favorite.data.local.remote

import com.ahj.onlineshop.core.sharedData.favorite.data.local.remote.dto.ProfileDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProfileApiService {

    @GET("user/{id}")
    suspend fun getProfileData(
        @Path("id") userId: String
    ): Response<ProfileDto>
}