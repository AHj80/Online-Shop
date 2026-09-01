package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.app.navigation.bottomNavigation.BottomNavData
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@Composable
fun BottomScreen(navController: NavController) {

    val bottomScreens = listOf(
        BottomNavData.HomeScreen,
        BottomNavData.Category,
        BottomNavData.Cart,
        BottomNavData.Profile,

        )

    NavigationBar(
        containerColor = Color.White
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentNav = backStackEntry?.destination

        bottomScreens.forEach { screen ->

            val selected = when (screen) {
                BottomNavData.HomeScreen -> {
                    currentNav?.hasRoute(Screens.HomeScreen::class) == true
                }

                BottomNavData.Profile -> {
                    currentNav?.hasRoute(Screens.UserProfile::class) == true
                }

                BottomNavData.Cart -> {
                    currentNav?.hasRoute(Screens.Cart::class) == true ||
                            currentNav?.hasRoute(Screens.CartConfirmAddress::class) == true
                }

                BottomNavData.Category -> {
                    currentNav?.hasRoute(Screens.Category::class) == true
                }

                else -> false
            }

            CompositionLocalProvider(LocalRippleConfiguration provides null) {

                NavigationBarItem(
                    interactionSource = remember { MutableInteractionSource() },
                    selected = selected,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ButtonColor_Tow,
                        selectedTextColor = ButtonColor_Tow,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    ),
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.height(35.dp)
                        ) {
                            AnimatedVisibility(
                                visible = selected,
                                enter = expandHorizontally(animationSpec = tween(500)),
                                exit = shrinkHorizontally(animationSpec = tween(500))
                            ) {
                                HorizontalDivider(
                                    thickness = 2.5.dp,
                                    modifier = Modifier.width(30.dp),
                                    color = ButtonColor_Tow
                                )
                            }
                            SpacerHeight(5)
                            Spacer(Modifier.weight(1f))
                            Icon(
                                painter = painterResource(screen.icon),
                                contentDescription = screen.title,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            screen.title,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    onClick = {
                        navController.navigate(
                            screen.route
                        ) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }


    }

}