package com.ahj.onlineshop.app.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    object Splash : Screens()

    @Serializable
    object Login : Screens()

    @Serializable
    object Register : Screens()

    @Serializable
    object FoundEmail : Screens()

    @Serializable
    data class EmailOTP(val id: String) : Screens()

    @Serializable
    data class ResetPassword(val id: String): Screens()

    @Serializable
    object HomeScreen : Screens()

}