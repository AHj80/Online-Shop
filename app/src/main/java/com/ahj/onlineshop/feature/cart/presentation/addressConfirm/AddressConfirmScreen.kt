package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.feature.authentication.component.CustomAlertDialog
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.cart.component.AddressItem
import com.ahj.onlineshop.feature.cart.component.CartDetail
import com.ahj.onlineshop.feature.cart.component.CustomOutlinedButton


@Composable
fun AddressConfirmScreen(
    navController: NavController,
    viewModel: AddressConfirmViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.defaultAddress) {
        viewModel.getDataAddress(uiState.defaultAddress)
    }

    DrawCircleBackground(
        blur = uiState.status == AddressConfirmStatus.LOADING || uiState.resultPaymentDialog
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SpacerHeight(20)
            InsertTitle("سبد خرید")
            SpacerHeight(20)

            when (uiState.status) {
                AddressConfirmStatus.EMPTY -> {

                    uiState.message?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                    OutlinedButton(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            navController.navigate(Screens.EditAddressScreen)
                        },
                        border = BorderStroke(width = 1.dp, color = ButtonColor_Tow),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)

                    ) {
                        Text(
                            "ثبت یا تغییر آدرس",
                            style = MaterialTheme.typography.titleSmall,
                            color = ButtonColor_Tow
                        )
                    }
                }

                AddressConfirmStatus.SUCCESS -> {

                    uiState.address?.let {
                        AddressItem(it)
                    }
                    SpacerHeight(10)
                    CustomOutlinedButton("به آدرس دیگری برود") {
                        navController.navigate(Screens.EditAddressScreen)
                    }

                    Text(
                        "تقریبا تا هفت روز کاری به دست شما میرسد",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }

                AddressConfirmStatus.LOADING -> {
                    InsertDialog({}, "در حال بررسی")
                }

                AddressConfirmStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) { viewModel.getDataAddress(null) }
                }
            }

            val data = uiState.data
            val address = uiState.address
            Spacer(Modifier.weight(1f))
            if (data != null && address != null) {
                CartDetail(
                    data.cartPrice,
                    data.cartFinalPrice,
                    data.cartDiscount,
                    true,
                    "پرداخت نهایی",
                    enabled = address.receiver.isNotBlank()
                ) {
                    viewModel.buyProducts()
                }
            }
        }

        if (uiState.resultPaymentDialog) {
            CustomAlertDialog(uiState.paymentText ?: "") {
                viewModel.changePaymentDialog()

            }
        }

        LaunchedEffect(uiState.navigating) {
            if (uiState.navigating){
                navController.navigate(Screens.HomeScreen) {
                    popUpTo<Screens.CartConfirmAddress> {
                        inclusive = true
                    }
                }
            }
        }

    }
}

