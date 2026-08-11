package com.ahj.onlineshop.feature.product.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahj.onlineshop.R
import com.ahj.onlineshop.app.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    backStack : () -> Unit = {},
    menu : () -> Unit = {},
    notification:()-> Unit = {},
    profile:()-> Unit = {}
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_online_shop_text),
                        contentDescription = null,
                        modifier = Modifier.width(80.dp)
                    )


                    Image(
                        painter = painterResource(R.drawable.logo_online_shop),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        },
        actions = {

            currentDestination?.hasRoute<Screens.HomeScreen>()?.let {
                AnimatedVisibility(
                    visible = !it,
                    enter = expandHorizontally(animationSpec = tween(300)),
                    exit = shrinkHorizontally(animationSpec = tween(300))
                ) {
                    IconButton(
                        {backStack()}, modifier = Modifier
                            .padding(end = 10.dp)
                            .size(30.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowForward,
                            null,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }
            }
        },

        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    {menu()},
                    modifier = Modifier
                        .size(40.dp)
                ) {

                    Icon(
                        Icons.Default.Menu,
                        null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    {notification()},
                    modifier = Modifier
                        .size(40.dp)
                ) {

                    Icon(
                        Icons.Outlined.Notifications,
                        null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }


                IconButton(
                    {profile()},
                    modifier = Modifier
                        .size(50.dp)
                ) {

                    Icon(
                        Icons.Default.AccountCircle,
                        null,
                        tint = Color(0xFFFF8563),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}
