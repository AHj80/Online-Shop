package com.ahj.onlineshop.core.common.ui.component

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ahj.onlineshop.R
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.openUrl
import com.ahj.onlineshop.core.common.utils.singleScreen
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    profileData: HeaderDataModel?,
    currentDestination: NavDestination?,
    logoutClick: () -> Unit,
    backStack: () -> Unit = {}

) {


    var stateNotification by remember { mutableStateOf(false) }
    var stateMenu by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
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
                val bottomScreen = listOf(
                    Screens.HomeScreen::class,
                    Screens.Cart::class,
                    Screens.UserProfile::class,
                    Screens.Category::class
                )
                val showBackStack = currentDestination != null && bottomScreen.any { route ->
                    currentDestination.hasRoute(route)
                }

                    AnimatedVisibility(
                        visible = !showBackStack,
                        enter = expandHorizontally(animationSpec = tween(300)),
                        exit = shrinkHorizontally(animationSpec = tween(300))
                    ) {
                        IconButton(
                            { backStack() }, modifier = Modifier
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

                },

                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            {
                                stateMenu = !stateMenu

                            },
                            modifier = Modifier
                                .size(40.dp)
                        ) {


                            CustomAnimate(stateMenu,100 , 100) {
                                Icon(
                                    Icons.Default.Close,
                                    null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            CustomAnimate(!stateMenu , 100 , 100) {
                                Icon(
                                    Icons.Default.Menu,
                                    null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )

                            }
                        }

                        IconButton(
                            { stateNotification = !stateNotification },
                            modifier = Modifier
                                .size(40.dp)
                        ) {

                            CustomAnimate(stateNotification,100 , 100) {

                                Icon(
                                    Icons.Outlined.Close,
                                    null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            CustomAnimate(!stateNotification,100 , 100) {
                                Icon(
                                    Icons.Outlined.Notifications,
                                    null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }


                        IconButton(
                            {
                                navController.navigate(Screens.UserProfile) {
                                    popUpTo(Screens.HomeScreen) {
                                        saveState = true

                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier
                                .size(50.dp)
                        ) {

                            CustomAsyncImage(profileData?.avatar)
                        }

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropDownMenuNotification(stateNotification) { stateNotification = false }
                    DropDownMenuBar(
                        stateMenu, navController,
                        profileData?.avatar,
                        profileData?.profile?.name,
                        profileData?.profile?.phone,
                        logoutClick = { logoutClick() }
                    ) { stateMenu = false }
                }
            }

    }


    @Composable
    private fun DropDownMenuNotification(
        expanded: Boolean,
        onDismiss: () -> Unit
    ) {
        DropdownMenu(
            expanded = expanded,
            {
                onDismiss()
            },
            containerColor = Color.White,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    "در حال حاضر هیچ اعلان فعالی وجود ندارد!",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 10.sp
                )
            }

        }
    }


    @Composable
    private fun DropDownMenuBar(
        expanded: Boolean,
        navController: NavController,
        avatar: Uri?,
        name: String?,
        phone: String?,
        logoutClick: () -> Unit,
        onDismiss: () -> Unit
    ) {
        val listBar = remember {
            listOf(
                MenuModel(0, "پروفایل", R.drawable.profile),
                MenuModel(1, "سفارشات من", R.drawable.user_order),
                MenuModel(2, "درباره ما", R.drawable.about),
                MenuModel(3, "ارتباط با ما", R.drawable.tell_us),
                MenuModel(4, "پشتیبانی", R.drawable.support),
                MenuModel(5, "خروج", R.drawable.logout)
            )
        }
        val context = LocalContext.current

        val animate = animateColorAsState(
            if (expanded) ButtonColor_Tow else Color.White,
            tween(1000)
        )



        DropdownMenu(
            expanded,
            onDismiss,
            containerColor = Color.White,
            shadowElevation = 10.dp,
            offset = DpOffset(x = 10.dp, 0.dp),
            modifier = Modifier
                .heightIn(max = 350.dp),
            border = BorderStroke(1.dp, animate.value)
        ) {


            Column {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BackgroundCircleColor),
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomAsyncImage(avatar)

                        Text(
                            name ?: "",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp)
                        )

                        Text(
                            phone?.toPersianDigit() ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 10.sp
                        )

                    }
                }
                listBar.forEach { menuItems ->

                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(end = 80.dp)
                            ) {
                                Icon(
                                    painter = painterResource(menuItems.image),
                                    contentDescription = null,
                                    tint = ButtonColor_Tow,
                                    modifier = Modifier
                                        .padding(end = 12.dp)
                                        .size(20.dp)
                                )
                                Text(
                                    text = menuItems.title,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        },
                        onClick = {
                            onDismiss()
                            when (menuItems.id) {

                                0 -> {
                                    navController.singleScreen(Screens.UserProfile)
                                }

                                1 -> {
                                    navController.singleScreen(Screens.UserOrderScreen)
                                }

                                2 -> {
                                    context.openUrl(
                                        "https://github.com/AHj80?tab=repositories"
                                    )
                                }

                                3 -> {
                                    context.openUrl(
                                        "https://t.me/AHj80",
                                        "org.telegram.messenger"
                                    )
                                }

                                4 -> {
                                    context.openUrl(
                                        "https://t.me/AHj80",
                                        "org.telegram.messenger"
                                    )
                                }

                                5 -> {
                                    logoutClick()
                                }
                            }
                        }
                    )

                }
            }
        }
    }


    @Composable
    private fun CustomAsyncImage(avatar: Uri?) {
        if (avatar != null) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                shadowElevation = 10.dp,

                ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatar)
                        .crossfade(true)
                        .build(),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            Icon(
                Icons.Default.AccountCircle,
                null,
                tint = Color(0xFFFF8563),
                modifier = Modifier.size(40.dp)
            )
        }
    }

