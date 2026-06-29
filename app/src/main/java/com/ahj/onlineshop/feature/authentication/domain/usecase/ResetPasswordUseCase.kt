package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject


class ResetPasswordUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: String, pass: String): Result<Boolean> {

       return repository.resetPassword(id, pass).fold(
            onSuccess = {
                Result.success(true)
            },
            onFailure = {
                Result.failure(Exception("خطا: ${it.message}"))
            }
        )

    }
}