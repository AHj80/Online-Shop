package com.ahj.onlineshop.app.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahj.onlineshop.app.base.MainViewModel
import com.ahj.onlineshop.core.common.ui.component.BottomScreen
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.CustomTopAppBar
import com.ahj.onlineshop.feature.authentication.presentation.foundEmail.FoundEmailScreen
import com.ahj.onlineshop.feature.authentication.presentation.login.LoginScreen
import com.ahj.onlineshop.feature.authentication.presentation.otp.EmailOTPScreen
import com.ahj.onlineshop.feature.authentication.presentation.register.RegisterScreen
import com.ahj.onlineshop.feature.authentication.presentation.resetPassword.ResetPasswordScreen
import com.ahj.onlineshop.feature.authentication.presentation.splash.SplashScreen
import com.ahj.onlineshop.feature.cart.presentation.addressConfirm.AddressConfirmScreen
import com.ahj.onlineshop.feature.cart.presentation.cart.CartScreen
import com.ahj.onlineshop.feature.cart.presentation.changeAddress.EditAddressScreen
import com.ahj.onlineshop.feature.product.presentation.category.CategoryScreen
import com.ahj.onlineshop.feature.product.presentation.detailProduct.DetailProductScreen
import com.ahj.onlineshop.feature.product.presentation.home.HomeScreen
import com.ahj.onlineshop.feature.product.presentation.listProduct.ListProductScreen
import com.ahj.onlineshop.feature.product.presentation.subCategory.SubCategoryScreen
import com.ahj.onlineshop.feature.profile.presentation.changePassword.ChangePasswordScreen
import com.ahj.onlineshop.feature.profile.presentation.favorites.FavoriteScreen
import com.ahj.onlineshop.feature.profile.presentation.notification.NotificationScreen
import com.ahj.onlineshop.feature.profile.presentation.userAddress.ProfileAddressScreen
import com.ahj.onlineshop.feature.profile.presentation.userExperience.UserExperiencesScreen
import com.ahj.onlineshop.feature.profile.presentation.userOrders.UserOrdersScreen
import com.ahj.onlineshop.feature.profile.presentation.userProfile.UserProfileScreen


@Composable
fun SetupUI(viewModel: MainViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
    val screenBottomBar = listOf(
        Screens.HomeScreen::class,
        Screens.Category::class,
        Screens.Cart::class,
        Screens.UserProfile::class,
        Screens.CartConfirmAddress::class
    )

    Scaffold(
        topBar = {
            val authScreens = screenNonScaffold.any { currentDes?.hasRoute(it) == true }
            if (!authScreens)
                CustomTopAppBar(navController, uiState.data,backStack = { navController.popBackStack() })
        },
        bottomBar = {
            val bottomBarVisible =
                screenBottomBar.any { currentDes?.hasRoute(it) == true }
            CustomAnimate(bottomBarVisible) { BottomScreen(navController) }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.Splash,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
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
            composable<Screens.Cart> {
                HomeScreen(navController)
            }
            composable<Screens.UserProfile> {
                HomeScreen(navController)
            }



            composable<Screens.SubCategory> {
                val input = it.toRoute<Screens.SubCategory>()
                SubCategoryScreen(
                    navController = navController,
                    parentCategory = input.parentCategory,

                    )
            }

            composable<Screens.ListProductScreen> {
                val input = it.toRoute<Screens.ListProductScreen>()
                ListProductScreen(
                    navController,
                    subCategoryType = input.subCategoryType,
                    parentCategory = input.parentCategory
                )
            }

            composable<Screens.DetailProduct> {
                val input = it.toRoute<Screens.DetailProduct>()
                DetailProductScreen(
                    navController = navController,
                    id = input.id,
                    categoryType = input.categoryType
                )
            }

            composable<Screens.UserProfile> {
                UserProfileScreen(navController)
            }

            composable<Screens.Cart> {
                CartScreen(navController = navController)
            }

            composable<Screens.CartConfirmAddress> {
                AddressConfirmScreen(navController)
            }

            composable<Screens.EditAddressScreen> {
                EditAddressScreen(navController)
            }

            composable<Screens.FavoriteScreen> {
                FavoriteScreen(navController)
            }

            composable<Screens.ShoppingExperience> {
                UserExperiencesScreen()
            }
            composable<Screens.ProfileAddress> {
                ProfileAddressScreen()
            }
            composable<Screens.ChangePassword> {
                ChangePasswordScreen()
            }

            composable<Screens.UserOrderScreen> {
                UserOrdersScreen(navController = navController)
            }

            composable<Screens.Notification> {
                NotificationScreen()
            }

        }

    }

}
