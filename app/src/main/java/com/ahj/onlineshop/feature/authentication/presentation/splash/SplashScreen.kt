package com.ahj.onlineshop.feature.authentication.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.InsertButton
import com.ahj.onlineshop.core.common.ui.component.Progress
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.authFeature.DrawCircleBackground
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertLogo


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checking()
    }

    DrawCircleBackground(
        uiState.statusSplash == StatusSplash.LOADING
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InsertLogo(1f, 1f, 1f)
            SpacerHeight(10)

            when (uiState.statusSplash) {

                StatusSplash.LOADING -> {

                    Progress()
                }

                StatusSplash.LOGIN_YES -> {


                    navController.navigate(Screens.HomeScreen) {
                        popUpTo(
                            Screens.Splash
                        ) { inclusive = true }
                    }
                }

                StatusSplash.LOGIN_NO -> {
                    navController.navigate(Screens.Register) {
                        popUpTo(
                            Screens.Splash
                        ) { inclusive = true }
                    }
                }
                else -> {}
            }
            AnimatedVisibility(
                visible = uiState.statusSplash == StatusSplash.ERROR,
                enter = expandVertically(tween(500)),
                exit = shrinkVertically(tween(500))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InsertButton("بررسی مجدد"  , padding = 90) {
                        viewModel.checking()
                        viewModel.tryAgain()
                    }
                    SpacerHeight(20)
                    Text(
                        "خطا در برقراری ارتباط",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                    )
                }
            }

        }
    }

}