package com.ahj.onlineshop.feature.authentication.data.remote

import com.ahj.onlineshop.feature.authentication.data.remote.dto.LoginDto
import com.ahj.onlineshop.feature.authentication.data.remote.dto.RegisterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @GET("user")
    suspend fun getByEmailForLogin(
        @Query("email") email: String
    ): Response<List<LoginDto>>

    @POST("user")
    suspend fun register(
        @Body body: RegisterDto
    ) : Response<RegisterDto>

    @GET("user")
    suspend fun getByEmailForReg(
        @Query("email") email: String
    ) : Response<List<RegisterDto>>

    @PATCH("user/{id}")
    suspend fun resetPassword(
        @Path("id") userId : String,
        @Body updatePass : Map<String , String>
    ) : Response<LoginDto>


}