package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject


class VerifyUseCase @Inject constructor(
) {

    operator fun invoke(input: String, server: String?): Result<Boolean> {

      return  if (input.length < 4) {
            Result.failure(Exception("کد تایید باید 4 رقم باشد"))
        } else if (input == server){
          Result.success(true)

      }else
          Result.failure(Exception(if (server.isNullOrBlank()) "کد منقضی شده است" else "کد وارد شده صحیح نمیباشد"))

    }
}