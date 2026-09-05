package com.ahj.onlineshop.core.common.utils

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ApiHelper @Inject constructor() {

   suspend fun <T> safeData(request :suspend ()-> Response<T>): Result<T?>{

      return try {
          val response = request()
         if (response.isSuccessful){
            Result.success(response.body())
         } else if (response.code() == 404){
            Result.success(null)
         } else {
            Result.failure(Exception(" خطای سرور : ${response.code()}"))
         }
      } catch (e: Exception){
         val customException = when (e) {
            is SocketTimeoutException -> Exception("زمان پاسخگویی سرور به پایان رسید. لطفاً دوباره تلاش کنید")
            is IOException -> Exception("اتصال اینترنت خود را بررسی کنید")
            is HttpException -> Exception(e.code().toString())
            is SerializationException -> Exception("Malformed Json")
            else -> Exception("خطایی در برقراری ارتباط رخ داد: ${e.message}")
         }
         Result.failure(customException)
      }

   }
}

