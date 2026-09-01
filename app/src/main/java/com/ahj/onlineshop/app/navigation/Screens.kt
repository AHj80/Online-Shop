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
    data class ResetPassword(val id: String) : Screens()

    @Serializable
    object HomeScreen : Screens()

    @Serializable
    object Category : Screens()

    @Serializable
    data class SubCategory(val parentCategory: String)

    @Serializable
    data class ListProductScreen(
        val subCategoryType: String,
        val parentCategory: String
    )

    @Serializable
    data class DetailProduct(val id: String, val categoryType: String)

    @Serializable
    object Cart : Screens()

    @Serializable
    object CartConfirmAddress : Screens()

    @Serializable
    object EditAddressScreen : Screens()

    @Serializable
    object UserProfile : Screens()

    @Serializable
    object EditProfile : Screens()

    @Serializable
    object FavoriteScreen : Screens()

    @Serializable
    object ShoppingExperience : Screens()

    @Serializable
    object ProfileAddress : Screens()

    @Serializable
    object ChangePassword: Screens()

    @Serializable
    object UserOrderScreen: Screens()

    @Serializable
    object Notification: Screens()
}

