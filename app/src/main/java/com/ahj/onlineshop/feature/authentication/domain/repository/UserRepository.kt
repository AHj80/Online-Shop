package com.ahj.onlineshop.feature.authentication.domain.repository

import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel


interface UserRepository {

  suspend fun login(email: String) : Result<LoginModel?>

  suspend fun register(register: RegisterModel) : Result<RegisterModel?>

  suspend fun getByEmail(email: String) : Result<RegisterModel?>

  suspend fun resetPassword(userId: String , newPass: String): Result<LoginModel?>
  
  suspend fun  sendOtpCode(): Result<String>

    fun verifyOtp(input: String, severCode: String)

}