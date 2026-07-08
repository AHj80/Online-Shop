package com.ahj.onlineshop.core.common.utils

import javax.inject.Inject

class ApiHelperOffline @Inject constructor() {

    suspend fun <T> safeData(request: suspend () -> T): Result<T> {

        return try {
            val response = request()
            Result.success(response)
        } catch (e: Exception) {

            Result.failure(Exception("خطا در دریافت اطلاعات صفحه اصلی\n${e.message}"))
        }

    }
}