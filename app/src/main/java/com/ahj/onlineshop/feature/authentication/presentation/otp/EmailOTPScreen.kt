package com.ahj.onlineshop.feature.authentication.presentation.otp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.InsertButton
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.component.authFeature.DrawCircleBackground
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertBody
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertLogo
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTextFieldAuth
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTitle
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.core.common.utils.toPersianDigit


@Composable
fun EmailOTPScreen(
    navController: NavController,
    viewModel: OTPConfirmViewModel = hiltViewModel(),
    id: String
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .blur(if (uiState.status == OTPConfirmStatus.LOADING) 10.dp else 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InsertLogo()
            SpacerHeight(30)

            InsertTitle(
                "کد تایید :"
            )
            SpacerHeight(30)

            InsertBody("لطفا کد تایید 4 رقمی ارسال شده به آدرس ایمیل  را وارد نمایید".toPersianDigit())
            SpacerHeight(10)
            Box(
                contentAlignment = Alignment.Center
            ) {
                InsertTextFieldAuth(
                    value = uiState.stateOTP,
                    onValueChange = { viewModel.stateOTPChanged(it) },
                    placeholder = "کد تایید را وارد نمایید",
                    keyboardType = KeyboardType.Number,
                    supportingText = uiState.message,
                    isError = uiState.status == OTPConfirmStatus.ERROR
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(end = 50.dp, bottom = 10.dp)
                            .width(50.dp)
                            .height(30.dp),
                        colors = CardDefaults.cardColors(containerColor = BackgroundCircleColor),

                        ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val minutes = uiState.timer / 60
                            val seconds = uiState.timer % 60
                            val formattedTime = String.format(java.util.Locale.US, "%02d:%02d", minutes, seconds)
                            Text(
                                formattedTime.toPersianDigit(),
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
            }
            SpacerHeight(30)

            InsertButton(
                "ادامه",
                enabled = viewModel.enabledChange(uiState.stateOTP)
            ) {
                viewModel.otpChecking(uiState.stateOTP)
            }
            SpacerHeight(10)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "ویرایش ایمیل",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()

                        }
                )
                SpacerWith(30)

                Text(
                    "ارسال مجدد کد",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    color = if (uiState.reSendCode) Color.Black else Color.LightGray,
                    modifier = Modifier
                        .clickable {
                            viewModel.sendOtpCode()
                        }
                )


            }


        }

        when (uiState.status) {
            OTPConfirmStatus.IDLE -> {}
            OTPConfirmStatus.ERROR -> {}
            OTPConfirmStatus.SUCCESS -> {

                navController.navigate(Screens.ResetPassword(id)){
                    popUpTo<Screens.EmailOTP> { inclusive = true }
                }
            }
            OTPConfirmStatus.LOADING -> {
                InsertDialog({ viewModel.onDismiss() }, "لطفا منتظر بمانید")
            }
        }

    }

}