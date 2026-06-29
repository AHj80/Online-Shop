package com.ahj.onlineshop.feature.authentication.domain.usecase

import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): Result<String> {
        return repository.sendOtpCode()
    }
}