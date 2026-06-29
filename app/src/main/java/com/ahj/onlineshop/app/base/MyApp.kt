package com.ahj.onlineshop.app.base

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    companion object {
        const val AUTH_OTP = "auth_Otp"
    }

    override fun onCreate() {
        super.onCreate()

        val notificationManager = getSystemService(NotificationManager::class.java)

        val channelOtp = NotificationChannel(
            AUTH_OTP,
            "کد احراز هویت",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channelOtp)

    }
}