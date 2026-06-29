package com.ahj.onlineshop.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahj.onlineshop.feature.authentication.presentation.foundEmail.FoundEmailScreen
import com.ahj.onlineshop.feature.authentication.presentation.login.LoginScreen
import com.ahj.onlineshop.feature.authentication.presentation.otp.EmailOTPScreen
import com.ahj.onlineshop.feature.authentication.presentation.register.RegisterScreen
import com.ahj.onlineshop.feature.authentication.presentation.resetPassword.ResetPasswordScreen
import com.ahj.onlineshop.feature.authentication.presentation.splash.SplashScreen


@Composable
fun SetupUI() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash
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
            val id = it.toRoute<Screens.EmailOTP>()
            EmailOTPScreen(navController , id = id.id)
        }

        composable <Screens.ResetPassword> {
            val id = it.toRoute<Screens.ResetPassword>()
            ResetPasswordScreen( navController ,id = id.id)
        }

        composable <Screens.HomeScreen> {

        }

    }

}