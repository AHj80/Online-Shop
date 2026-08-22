package com.ahj.onlineshop.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.BottomScreen
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.theme.OnlineShopTheme
import com.ahj.onlineshop.feature.cart.presentation.addressConfirm.AddressConfirmScreen
import com.ahj.onlineshop.feature.cart.presentation.cart.CartScreen
import com.ahj.onlineshop.feature.cart.presentation.changeAddress.EditAddressScreen
import com.ahj.onlineshop.feature.product.presentation.category.CategoryScreen
import com.ahj.onlineshop.feature.product.presentation.detailProduct.DetailProductScreen
import com.ahj.onlineshop.feature.product.presentation.home.HomeScreen
import com.ahj.onlineshop.feature.product.presentation.listProduct.ListProductScreen
import com.ahj.onlineshop.feature.product.presentation.subCategory.SubCategoryScreen
import com.ahj.onlineshop.feature.profile.presentation.favorites.FavoriteScreen
import com.ahj.onlineshop.feature.profile.presentation.userExperience.UserExperiencesScreen
import com.ahj.onlineshop.feature.profile.presentation.userProfile.UserProfileScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OnlineShopTheme {

                val navController = rememberNavController()
                val screenBottomBar = listOf(
                    Screens.HomeScreen::class,
                    Screens.Category::class,
                    Screens.Cart::class,
                    Screens.UserProfile::class,
                )
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDes = backStackEntry?.destination
                Scaffold(
                    bottomBar = {
                        val bottomBarVisible =
                            screenBottomBar.any { currentDes?.hasRoute(it) == true }
                        CustomAnimate(bottomBarVisible) { BottomScreen(navController) }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screens.HomeScreen,
                        modifier = Modifier
                            .consumeWindowInsets(innerPadding)
                            .padding(innerPadding)
                    ) {

                        composable<Screens.HomeScreen> {
                            HomeScreen(navController)
                        }
                        composable<Screens.Category> {
                            CategoryScreen(navController)
                        }
                        composable<Screens.Cart> {
                            CartScreen(navController = navController)
                        }
                        composable<Screens.CartConfirmAddress> {
                            AddressConfirmScreen(navController = navController)
                        }

                        composable<Screens.EditAddressScreen> {
                            EditAddressScreen(navController)
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

                        composable<Screens.EditProfile> { }

                        composable<Screens.FavoriteScreen> {
                            FavoriteScreen(navController)
                        }

                        composable<Screens.ShoppingExperience> {
                            UserExperiencesScreen()
                        }

                        composable <Screens.ProfileAddress>{

                        }
                    }
                }

                /*val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.DetailProduct("1" , "man_sport")
                ){
                    composable<Screens.UserProfile> {
                        UserProfileScreen(navController)
                    }

                    composable<Screens.EditProfile> { }

                    composable<Screens.FavoriteScreen> {
                        FavoriteScreen()
                    }


                    composable<Screens.DetailProduct> {
                        val input = it.toRoute<Screens.DetailProduct>()
                        DetailProductScreen(
                            navController = navController,
                            id = "1",
                            categoryType = "man_sport"
                        )
                    }

                }*/

            }
        }
    }
}