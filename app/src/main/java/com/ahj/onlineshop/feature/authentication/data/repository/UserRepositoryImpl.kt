package com.ahj.onlineshop.feature.authentication.data.repository

import com.ahj.onlineshop.core.common.utils.ApiHelper
import com.ahj.onlineshop.feature.authentication.data.mapper.toDomain
import com.ahj.onlineshop.feature.authentication.data.mapper.toDto
import com.ahj.onlineshop.feature.authentication.data.remote.UserApiService
import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel
import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val apiHelper: ApiHelper
) : UserRepository {

    override suspend fun login(email: String): Result<LoginModel?> {

        val result = apiHelper.safeData {
            userApiService.getByEmailForLogin(email)
        }
        return result.map { it?.firstOrNull()?.toDomain() }
    }

    override suspend fun register(register: RegisterModel): Result<RegisterModel?> {

        val result = apiHelper.safeData {
            userApiService.register(register.toDto())
        }
        return result.map { it?.toDomain() }
    }


    override suspend fun getByEmail(
        email: String,
    ): Result<RegisterModel?> {

        val result = apiHelper.safeData {
            userApiService.getByEmailForReg(
                email = email
            )
        }
        return result.map { it?.firstOrNull()?.toDomain() }
    }


    override suspend fun resetPassword(userId: String, newPass: String): Result<LoginModel?> {

        val result = apiHelper.safeData {
            userApiService.resetPassword(
                userId = userId,
                updatePass = mapOf("password" to newPass)
            )
        }

        return result.map { it?.toDomain() }

    }

    override suspend fun sendOtpCode(): Result<String> {

        return try {
            delay(3000)
            val generatedCode = (1000..9999).random().toString()
            Result.success(generatedCode)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override fun verifyOtp(input: String, severCode: String) {

    }


}