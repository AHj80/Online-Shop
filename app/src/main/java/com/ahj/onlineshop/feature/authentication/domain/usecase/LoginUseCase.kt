package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val repository: UserRepository,

) {

    suspend operator fun invoke(loginModel: LoginModel): Result<LoginModel?> =

        repository.login(loginModel.email).fold(
            onSuccess = { data ->
                if (data == null)
                    Result.failure(Exception("حساب کاربری یافت نشد"))
                else if(data.email == loginModel.email && data.password == loginModel.password)
                    Result.success(data)
                else Result.failure(Exception("نام کاربری یا رمز عبور اشتباه است"))
            },
            onFailure = {
                Result.failure(it)
            }
        )

}