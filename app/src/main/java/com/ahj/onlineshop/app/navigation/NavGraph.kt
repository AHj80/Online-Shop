package com.ahj.onlineshop.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahj.onlineshop.core.common.ui.component.productFeature.CustomTopAppBar
import com.ahj.onlineshop.feature.authentication.presentation.foundEmail.FoundEmailScreen
import com.ahj.onlineshop.feature.authentication.presentation.login.LoginScreen
import com.ahj.onlineshop.feature.authentication.presentation.otp.EmailOTPScreen
import com.ahj.onlineshop.feature.authentication.presentation.register.RegisterScreen
import com.ahj.onlineshop.feature.authentication.presentation.resetPassword.ResetPasswordScreen
import com.ahj.onlineshop.feature.authentication.presentation.splash.SplashScreen
import com.ahj.onlineshop.feature.product.presentation.category.CategoryScreen
import com.ahj.onlineshop.feature.product.presentation.home.HomeScreen
import com.ahj.onlineshop.feature.product.presentation.selectedCategory.SubCategoryScreen


@Composable
fun SetupUI() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDes = backStackEntry?.destination
    val screenNonScaffold = listOf(
        Screens.Splash::class,
        Screens.Login::class,
        Screens.EmailOTP::class,
        Screens.ResetPassword::class,
        Screens.FoundEmail::class,
        Screens.Register::class
    )

    Scaffold(
        topBar = {
            val authScreens = screenNonScaffold.any { currentDes?.hasRoute(it) == true }
            if (!authScreens)
                CustomTopAppBar(navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.Splash,
            modifier = Modifier.padding(innerPadding)
        ) {


            composable<Screens.Splash> {
                SplashScreen(navController = navController)
            }

            composable<Screens.Login> {
                LoginScreen(navController)
            }

            composable<Screens.Register> {
                RegisterScreen(navController)
            }

            composable<Screens.FoundEmail> {
                FoundEmailScreen(navController)
            }

            composable<Screens.EmailOTP> {
                val input = it.toRoute<Screens.EmailOTP>()
                EmailOTPScreen(navController, id = input.id)
            }

            composable<Screens.ResetPassword> {
                val input = it.toRoute<Screens.ResetPassword>()
                ResetPasswordScreen(navController, id = input.id)
            }

            composable<Screens.HomeScreen> {
                HomeScreen(navController)
            }
            composable<Screens.Category> {
                CategoryScreen(navController)
            }

            composable <Screens.SubCategory>{
                val input = it.toRoute<Screens.SubCategory>()
                SubCategoryScreen(parentCategory = input.parentCategory)
            }

            composable {  }
        }

    }

}
