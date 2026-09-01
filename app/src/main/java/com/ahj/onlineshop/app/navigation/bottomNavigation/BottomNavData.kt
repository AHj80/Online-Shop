package com.ahj.onlineshop.app.navigation.bottomNavigation

import com.ahj.onlineshop.R
import com.ahj.onlineshop.app.navigation.Screens

sealed class BottomNavData(
    val route: Any,
    val title: String,
    val icon: Int
) {

    object HomeScreen: BottomNavData(
        Screens.HomeScreen,
        "خانه",
        R.drawable.home_bottomnav
    )
    object Category: BottomNavData(
        Screens.Category,
        "دسته بندی",
        R.drawable.category_bottomnav
    )
    object Cart: BottomNavData(
        Screens.Cart,
        "سبد خرید",
        R.drawable.shopping_bottomnav
    )
    object Profile: BottomNavData(
        Screens.UserProfile,
        "پروفایل من",
        R.drawable.user_bottomnav
    )

}