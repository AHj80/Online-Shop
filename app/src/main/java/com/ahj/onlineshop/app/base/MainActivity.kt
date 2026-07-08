package com.ahj.onlineshop.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.theme.OnlineShopTheme
import com.ahj.onlineshop.feature.product.presentation.category.CategoryScreen
import com.ahj.onlineshop.feature.product.presentation.selectedCategory.SubCategoryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            OnlineShopTheme {

                    val navController = rememberNavController() // 🎯 تنها یک نمونه اصلی در کل اپلیکیشن

                    NavHost(
                        navController = navController,
                        startDestination = Screens.Category
                    ) {
                        composable<Screens.Category> {
                            CategoryScreen(navController = navController)
                        }

                        composable<Screens.SubCategory> {
                            val parentCat = it.toRoute<Screens.SubCategory>()

                            SubCategoryScreen(
                                parentCategory = parentCat.parentCategory
                            )
                        }
                    }

            }
        }
    }
}