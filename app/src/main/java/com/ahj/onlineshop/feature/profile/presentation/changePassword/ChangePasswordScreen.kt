package com.ahj.onlineshop.feature.profile.presentation.changePassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.CustomAlertDialog
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertBody
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground(
        uiState.loading && uiState.alertDialog
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            CustomAnimate(uiState.header?.profile != null, 200, 200) {
                uiState.header?.profile?.let {
                    TopAppProfileMini(uiState.header?.avatar, it)
                }
            }

            when (uiState.status) {
                ChangePasswordStatus.IDELE -> {}
                ChangePasswordStatus.LOADING -> {
                    InsertDialog(text = "در حال ارتباط")
                }

                ChangePasswordStatus.SUCCESS -> {

                    SpacerHeight(20)
                    InsertTitle("تغییر رمز عبور")
                    SpacerHeight(10)
                    InsertBody(
                        "رمز عبور باید شامل:\nحداقل 8 کاراکتر باشد\nشامل حروف و اعداد باشد\nاز حروف بزرگ و کوچک استفاده شود"
                    )
                    SpacerHeight(20)

                    InsertTitle("رمز عبور فعلی")
                    SpacerHeight(10)

                    InsertTextFieldAuth(
                        uiState.oldPass,
                        onValueChange = { viewModel.oldPassChange(it) },
                        placeholder = "رمز فعلی را وارد نمایید",
                        keyboardType = KeyboardType.Password,
                        isError = uiState.isError,
                        visualTransformation = uiState.visibleEye,
                        trailingIcon = {
                            IconButton({ viewModel.changingVisibleEye() }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_hide_and_show_pass),
                                    contentDescription = null,
                                )
                            }
                        }
                    )
                    SpacerHeight(20)

                    InsertTitle("رمز عبور جدید")
                    SpacerHeight(10)

                    InsertTextFieldAuth(
                        value = uiState.newPass,
                        onValueChange = { viewModel.newPassChange(it) },
                        placeholder = "رمز عبور جدید را کنید",
                        visualTransformation = uiState.visibleEye,
                        isError = uiState.isError,
                        keyboardType = KeyboardType.Password,
                        supportingText = if (uiState.confirmPass.isNotEmpty() && uiState.confirmPass != uiState.newPass) "رمزهای ورودی ناهمتا میباشند" else null
                    )
                    SpacerHeight(20)

                    InsertTitle("تکرار رمز عبور جدید")
                    SpacerHeight(10)

                    InsertTextFieldAuth(
                        value = uiState.confirmPass,
                        onValueChange = { viewModel.confirmPassChange(it) },
                        placeholder = "رمز عبور جدید را تکرار کنید",
                        visualTransformation = uiState.visibleEye,
                        isError = uiState.isError,
                        keyboardType = KeyboardType.Password,
                        supportingText = if (uiState.newPass.isNotEmpty() && uiState.confirmPass != uiState.newPass) "رمزهای ورودی ناهمتا میباشند" else null
                    )
                    SpacerHeight(30)

                    InsertButtonPrimary(
                        "تغییر رمز عبور",
                        enabled = viewModel.validating(
                            uiState.oldPass,
                            uiState.newPass,
                            uiState.confirmPass
                        )
                    ) {
                        uiState.header?.userId?.let {
                            viewModel.changingPassword(it, uiState.newPass, uiState.oldPass)
                        }
                    }
                }

                ChangePasswordStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) {
                        viewModel.getHeaderData()
                    }
                }
            }

            if (uiState.alertDialog) {
                uiState.message?.let {
                    CustomAlertDialog(it) { viewModel.changeAlertDialog(false) }
                }
            }

            if (uiState.loading) {
                InsertDialog(text = "در حال ارتباط")
            }


        }
    }

}