package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel
import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(registerModel: RegisterModel): Result<RegisterModel?> =

        repository.getByEmail(registerModel.email).fold(
            onSuccess = {
                if (it == null)
                    repository.register(registerModel)
                else
                    Result.failure(Exception("با ایمیل مورد نظر قبلا ثبت نام شده است"))
            },
            onFailure = {

                Result.failure(it)
            }
        )

}