package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject


class EmailOTPUseCase @Inject constructor(
    private val repository: UserRepository
) {

    val generateCode = (1000..9999).random()

    suspend fun processOTP(id: String , newPass: String){
        repository.resetPassword(id , newPass)

    }

}