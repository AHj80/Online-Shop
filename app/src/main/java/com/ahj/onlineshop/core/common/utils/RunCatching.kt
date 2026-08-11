package com.ahj.onlineshop.core.common.utils

import javax.inject.Inject

class RunCatching @Inject constructor() {

    suspend fun <T> safeData(request: suspend () -> T): Result<T> {
        return try {
            val data = request()
            if (data == null){

                Result.failure(Exception("خطای داخلی: داده ای از دیتابیس دریافت نشد"))
            }
            else {

                Result.success(data)
            }
        } catch (cancellation: Exception) {
            throw cancellation
        } catch (e: Exception){
            Result.failure(Exception("خطای داخلی : $e"))
        }
    }
}