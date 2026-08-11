package com.ahj.onlineshop.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.cart.component.CartDetail
import com.ahj.onlineshop.feature.cart.component.CartItemSample


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    DrawCircleBackground() {

        when (uiState.cartStatus) {
            CartStatus.EMPTY -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    uiState.message?.let {
                        Text(it, style = MaterialTheme.typography.titleLarge, color = Color.Gray)
                    }
                }
            }

            CartStatus.ERROR -> {
                ErrorRefreshing(uiState.message) { viewModel.getCartData() }
            }

            CartStatus.SUCCESS -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Text(
                        "سبد خرید",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        items(uiState.data.size) {
                            val data = uiState.data[it]
                            CartItemSample(
                                uiState.data[it],
                                {
                                    viewModel.increaseQuantity(data.id)
                                },
                                {
                                    viewModel.decreaseQuantity(data.id, data.quantity, data)
                                }
                            )

                        }

                    }

                    CartDetail(
                        uiState.price,
                        uiState.finalPrice - 100000,
                        uiState.discount,
                        text = "ادامه خرید"
                    ){
                        navController.navigate(Screens.CartConfirmAddress)
                    }
                }
            }

            CartStatus.LOADING -> {
                InsertDialog({} , "در حال بررسی")
            }
        }

    }

}
