package com.ahj.onlineshop.feature.profile.presentation.userOrders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini
import com.ahj.onlineshop.feature.profile.component.UserOrderItemSample


@Composable
fun UserOrdersScreen(
    viewModel: UserOrdersViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomAnimate(
                uiState.header != null,
                enter = 200,
                exit = 200
            ) {
                uiState.header?.profile?.let {
                    TopAppProfileMini(
                        uiState.header?.avatar,
                        it
                    )
                }
            }
            SpacerHeight(20)

            when (uiState.status) {
                UserOrdersStatus.EMPTY -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        uiState.message?.let {

                            Text(
                                it,
                                color = Color.Gray,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }

                UserOrdersStatus.LOADING -> {}

                UserOrdersStatus.SUCCESS -> {
                    InsertTitle("سفارشات من")

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            uiState.ordersData.size,
                            key = { uiState.ordersData[it].id?.toInt() ?: 0 }) {

                            OrdersAnimate {
                                UserOrderItemSample(
                                    uiState.ordersData[it],
                                    navigateOnClick = { id, categoryType ->
                                        navController.navigate(
                                            Screens.DetailProduct(
                                                id,
                                                categoryType
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }

                }

                UserOrdersStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) { viewModel.getAllData() }
                }
            }

        }


    }
}

@Composable
private fun OrdersAnimate(
    content: @Composable () -> Unit
) {
    val stateVisible = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = stateVisible,
        enter =
            expandHorizontally(
                tween(400),
                expandFrom = Alignment.CenterHorizontally
            ),
        exit =
            shrinkHorizontally(
                tween(400),
                shrinkTowards = Alignment.CenterHorizontally
            )
    ) {
        content()
    }
}


