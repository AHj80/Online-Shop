package com.ahj.onlineshop.feature.profile.presentation.userAddress

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.cart.component.CustomModal
import com.ahj.onlineshop.feature.cart.component.CustomOutlinedButton
import com.ahj.onlineshop.feature.cart.component.EditAddressItem
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini


@Composable
fun ProfileAddressScreen(
    viewModel: ProfileAddressViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()



    DrawCircleBackground {

        Column {
            CustomAnimate(uiState.header?.profile != null, enter = 200, exit = 200) {
                uiState.header?.profile?.let {
                    TopAppProfileMini(uiState.header?.avatar, it)
                }
            }

            when (uiState.status) {
                ProfileAddressStatus.EMPTY -> {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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


                ProfileAddressStatus.LOADING -> {
                    InsertDialog({}, "در حال دریافت داده")
                }

                ProfileAddressStatus.SUCCESS -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        item {
                            SpacerHeight(20)
                            InsertTitle("آدرس های من")
                        }
                        items(
                            uiState.allAddress.size,
                            key = { uiState.allAddress[it].id }
                        ) {
                            EditAddressItem(
                                address = uiState.allAddress[it],
                                modifier = Modifier
                                    .animateItem(
                                        fadeInSpec = tween(300),
                                        fadeOutSpec = tween(300),
                                        placementSpec = spring(stiffness = Spring.StiffnessLow)
                                    ),
                                selected = viewModel.getAddressDefault(uiState.allAddress[it].id),
                                selectedBorder = uiState.addressDefault == uiState.allAddress[it].id,
                                {
                                    viewModel.savingDefault(uiState.allAddress[it].id)
                                },
                                {
                                    viewModel.getAddressById(uiState.allAddress[it].id)
                                    viewModel.changeModal(true)
                                },
                                {
                                    viewModel.deleteAddress(uiState.allAddress[it])
                                }
                            )
                        }
                        item {
                            CustomOutlinedButton("اضافه کردن آدرس جدید") {
                                viewModel.resetInput()
                                viewModel.changeModal(true)

                            }
                        }
                    }

                }

                ProfileAddressStatus.ERROR -> {

                    ErrorRefreshing(uiState.message) {
                        viewModel.getAddressData()
                    }

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