package com.ahj.onlineshop.feature.profile.presentation.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.feature.authentication.component.CustomAlertDialog
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini

@Composable
fun EditUserProfileScreen(
    viewModel: EditUserProfileViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground(uiState.alertDialog || uiState.status == EditUserProfileStatus.LOADING) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()


        ) {
            CustomAnimate(uiState.header?.profile != null, 200, 200) {
                uiState.header?.profile?.let {

                    TopAppProfileMini(uiState.header?.avatar, it)
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .weight(1f)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
            ) {
                SpacerHeight(20)

                InsertTitle("نام و نام خانوادگی:")
                SpacerHeight(10)

                InsertTextFieldAuth(
                    value = uiState.stateName,
                    onValueChange = { viewModel.changeName(it) },
                    placeholder = ""
                )
                SpacerHeight(20)

                InsertTitle("شماره تماس:")
                SpacerHeight(10)

                InsertTextFieldAuth(
                    value = uiState.statePhone,
                    onValueChange = { viewModel.changePhone(it) },
                    placeholder = "",
                    keyboardType = KeyboardType.NumberPassword
                )
                SpacerHeight(20)

                InsertTitle("ایمیل:")
                SpacerHeight(10)

                InsertTextFieldAuth(
                    value = uiState.stateEmail,
                    onValueChange = { },
                    placeholder = "",
                    readOnly = true
                )
                SpacerHeight(20)

                InsertTitle(text = "جنسیت:")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    RadioButton(
                        selected = uiState.gender,
                        { viewModel.changeGender(true) },
                        colors = RadioButtonDefaults.colors(selectedColor = ButtonColor_Tow)
                    )

                    Text(
                        "مرد",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 15.sp
                    )

                    RadioButton(
                        selected = !uiState.gender,
                        { viewModel.changeGender(false) },
                        colors = RadioButtonDefaults.colors(selectedColor = ButtonColor_Tow)
                    )

                    Text(
                        "زن",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 15.sp
                    )
                }

                InsertButtonPrimary("ذخیره تغییرات") {
                    viewModel.updateInformation(
                        name = uiState.stateName,
                        phone = uiState.statePhone,
                        email = uiState.stateEmail,
                        gender = uiState.gender
                    )
                }
                SpacerHeight(10)

            }

        }

        when (uiState.status) {
            EditUserProfileStatus.IDELE -> {}

            EditUserProfileStatus.LOADING -> {
                InsertDialog(text = "در حال ارتباط")
            }

            EditUserProfileStatus.SUCCESS -> {}

            EditUserProfileStatus.ERROR -> {
                uiState.message?.let {
                    CustomAlertDialog(it) {
                        viewModel.loadData()
                        viewModel.closeAlertDialog()
                    }

                }
            }
        }

        Loading(uiState.isLoading)

        ResultProcessing(uiState.alertDialog ,uiState.message) {
            viewModel.closeAlertDialog()
        }
    }
}

@Composable
private fun Loading(state: Boolean) {
    if (state) {
        InsertDialog(text = "در حال ارتباط")
    }
}

@Composable
private fun ResultProcessing(state: Boolean , message: String? , onClick:()-> Unit) {
    if (state) {
        message?.let {
            CustomAlertDialog(it) {
                onClick()
            }
        }

    }
}