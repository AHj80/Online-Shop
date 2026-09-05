package com.ahj.onlineshop.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
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
import com.ahj.onlineshop.feature.profile.presentation.editProfile.EditUserProfileScreen
import com.ahj.onlineshop.feature.profile.presentation.favorites.FavoriteScreen
import com.ahj.onlineshop.feature.profile.presentation.notification.NotificationScreen
import com.ahj.onlineshop.feature.profile.presentation.userAddress.ProfileAddressScreen
import com.ahj.onlineshop.feature.profile.presentation.userExperience.UserExperiencesScreen
import com.ahj.onlineshop.feature.profile.presentation.userOrders.UserOrdersScreen
import com.ahj.onlineshop.feature.profile.presentation.userProfile.UserProfileScreen
import kotlinx.coroutines.launch

@Composable
fun SetupUI(viewModel: MainViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDes = backStackEntry?.destination


    val scope = rememberCoroutineScope()
    val showSnackBar: (String) -> Unit = {
        scope.launch {
            uiState.stateSnackBar.currentSnackbarData?.dismiss()
            uiState.stateSnackBar.showSnackbar(
                it,
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
        }
    }

    Scaffold(
        topBar = {
            val authScreens =
                currentDes != null && !screenNonScaffold.any { currentDes.hasRoute(it) }
            CustomAnimate(authScreens, 200, 200) {
                CustomTopAppBar(
                    navController,
                    uiState.data,
                    logoutClick = {
                        navController.navigate(Screens.Login) {
                            popUpTo(0) { inclusive = true }
                        }
                        viewModel.logout()
                    },
                    backStack = { navController.popBackStack() },
                    currentDestination = currentDes
                )
            }

        },
        bottomBar = {
            val bottomBarVisible =
                screenBottomBar.any { currentDes?.hasRoute(it) == true }
            CustomAnimate(bottomBarVisible) { BottomScreen(navController, currentDes) }
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            NavHost(
                navController = navController,
                startDestination = Screens.Splash,
                modifier = Modifier
                    .consumeWindowInsets(innerPadding),
                enterTransition = { defaultEnterTransition },
                exitTransition = { defaultExitTransition },

                ) {


                composable<Screens.Splash>(
                    enterTransition = { fadeIn(tween(600)) },
                    exitTransition = { fadeOut(animationSpec = tween(600)) },
                ) {
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
                    HomeScreen(
                        navController, snackBar = showSnackBar,
                    )
                }
                composable<Screens.Category> {
                    CategoryScreen(navController)
                }
                composable<Screens.Cart> {
                    CartScreen(
                        navController = navController,
                        showSnackBar = { showSnackBar(it) })
                }
                composable<Screens.UserProfile> {
                    UserProfileScreen(navController)
                }



                composable<Screens.SubCategory> {
                    SubCategoryScreen(
                        navController = navController,
                        showSnackBar = showSnackBar
                    )
                }

                composable<Screens.ListProductScreen> {
                    ListProductScreen(
                        navController,
                        showSnackBar = showSnackBar
                    )
                }

                composable<Screens.DetailProduct> {
                    val input = it.toRoute<Screens.DetailProduct>()
                    DetailProductScreen(
                        navController = navController,
                        id = input.id,
                        categoryType = input.categoryType,
                        showSnackBar = showSnackBar
                    )
                }


                composable<Screens.EditProfile> {
                    EditUserProfileScreen()
                }


                composable<Screens.CartConfirmAddress> {
                    AddressConfirmScreen(navController)
                }

                composable<Screens.EditAddressScreen> {
                    EditAddressScreen(navController, showSnackBar = showSnackBar)
                }

                composable<Screens.FavoriteScreen> {
                    FavoriteScreen(navController, showSnackBar = showSnackBar)
                }

                composable<Screens.ShoppingExperience> {
                    UserExperiencesScreen()
                }
                composable<Screens.ProfileAddress> {
                    ProfileAddressScreen(showSnackBar = showSnackBar)
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
            SnackbarHost(
                uiState.stateSnackBar,
                modifier = Modifier.align(Alignment.TopCenter)
            ) { data ->

                Snackbar(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    dismissActionContentColor = ButtonColor_Tow,
                    shape = CircleShape,
                    modifier = Modifier.padding(vertical = 30.dp, horizontal = 60.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            data.visuals.message,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 10.sp
                        )

                        IconButton({ data.dismiss() }) {
                            Icon(
                                Icons.Default.Close,
                                null,
                                tint = ButtonColor_One,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                }
            }
        }


    }


}

private val screenNonScaffold = listOf(
    Screens.Splash::class,
    Screens.Login::class,
    Screens.EmailOTP::class,
    Screens.ResetPassword::class,
    Screens.FoundEmail::class,
    Screens.Register::class
)
private val screenBottomBar = listOf(
    Screens.HomeScreen::class,
    Screens.Category::class,
    Screens.Cart::class,
    Screens.UserProfile::class,
    Screens.CartConfirmAddress::class
)

private val defaultEnterTransition = slideInHorizontally(
    initialOffsetX = { it / 4 },
    animationSpec = tween(400)
) + fadeIn(tween(400))

private val defaultExitTransition = slideOutHorizontally(
    targetOffsetX = { -it / 4 },
    animationSpec = tween(400)
) + fadeOut(tween(400))

