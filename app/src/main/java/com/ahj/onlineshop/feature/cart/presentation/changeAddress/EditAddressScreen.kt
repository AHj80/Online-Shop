package com.ahj.onlineshop.feature.cart.presentation.changeAddress

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.cart.component.CustomModal
import com.ahj.onlineshop.feature.cart.component.CustomOutlinedButton
import com.ahj.onlineshop.feature.cart.component.EditAddressItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAddressScreen(
    navController: NavController,
    viewModel: EditAddressViewModel = hiltViewModel()

) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    DrawCircleBackground{

        when (uiState.status) {
            EditAddressStatus.LOADING -> {
                InsertDialog({}, "در حال دریافت داده")
            }

            EditAddressStatus.EMPTY -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    uiState.message?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.LightGray
                        )
                    }

                    CustomOutlinedButton("اضافه کردن آدرس جدید") {
                        viewModel.resetInput()
                        viewModel.changeModal(true)

                    }
                }

            }

            EditAddressStatus.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        InsertTitle("آدرس های من")
                        SpacerHeight(20)
                        Text(
                            "آدرس مورد نظر خود را وارد نمایید",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    items(uiState.data.size, key = { uiState.data[it].id }) {
                        EditAddressItem(
                            address = uiState.data[it],
                            modifier = Modifier
                                .animateItem(
                                    fadeInSpec = tween(300),
                                    fadeOutSpec = tween(300),
                                    placementSpec = spring(stiffness = Spring.StiffnessLow)
                                ),
                            selected = viewModel.getAddressDefault(uiState.data[it].id),
                            selectedBorder = uiState.addressDefault == uiState.data[it].id,
                            {
                                viewModel.savingDefault(uiState.data[it].id)
                            },
                            {
                                viewModel.getAddressById(uiState.data[it].id)
                                viewModel.changeModal(true)
                            },
                            {
                                viewModel.deleteAddress(uiState.data[it])
                            }
                        )


                    }

                    item {
                        CustomOutlinedButton("اضافه کردن آدرس جدید") {
                            viewModel.resetInput()
                            viewModel.changeModal(true)

                        }
                    }


                    item {
                        if (uiState.data.isNotEmpty())
                            InsertButtonPrimary("انتخاب آدرس و ادامه خرید") { navController.popBackStack() }
                    }

                }

            }

            EditAddressStatus.ERROR -> {
                ErrorRefreshing(uiState.message) {
                    viewModel.getAllAddress()
                }
            }



        }
        if (uiState.modal) {
            CustomModal(
                uiState.stateReceiver,
                {
                    viewModel.updateTextReceiver(it)
                },
                uiState.stateAddress,
                {
                    viewModel.updateTextAddress(it)
                },
                uiState.statePhone,
                {
                    viewModel.updateTextPhone(it)
                },
                uiState.statePostalCode,
                {
                    viewModel.updateTextPostalCode(it)
                },
                { viewModel.changeModal(false) },
                {
                    viewModel.saveAddress(
                        id = uiState.currentId,
                        receiver = uiState.stateReceiver,
                        fullAddress = uiState.stateAddress,
                        phone = uiState.statePhone,
                        postalCode = uiState.statePostalCode
                    )
                    viewModel.changeModal(false)
                },
                closeButton = { viewModel.changeModal(false) },
                enabled = viewModel.checkingEnabled()
            )
        }

    }
}


