package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject


class FoundEmailUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(
        email: String
    ): Result<LoginModel?> {

        return repository.login(email).fold(
            onSuccess = { data ->
                if (data !== null)
                    Result.success(data)
                else Result.failure(Exception("هیچگونه حساب کاربری با ایمیل مورد نظر یافت نشد"))
            },
            onFailure = {
                Result.failure(it)
            }
        )

    }
}