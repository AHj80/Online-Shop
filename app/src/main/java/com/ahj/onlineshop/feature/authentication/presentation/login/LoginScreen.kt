package com.ahj.onlineshop.feature.authentication.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.R
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


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var visibleEye by remember { mutableStateOf(false) }

    DrawCircleBackground {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .blur(if (uiState.loginStatus == LoginStatus.LOADING) 10.dp else 0.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerHeight(50)
            InsertLogo(0.7f, 0.7f, 0.7f)
            SpacerHeight(80)

            InsertBody("جهت ورود به فروشگاه اینترنتی آنلاین شاپ ایمیل خود را در کادر زیر وارد کرده و کد ارسالی به ایمیل خود را در مرحله بعد وارد کنید.")
            SpacerHeight(50)

            InsertTitle("ایمیل:")
            SpacerHeight(10)

            InsertTextFieldAuth(
                uiState.stateEmail,
                onValueChange = { viewModel.changeEmail(it) },
                placeholder = "لطفا ایمیل خود را وارد نمایید",
                supportingText = uiState.message,
                isError = uiState.isError,
                keyboardType = KeyboardType.Email,
            )
            SpacerHeight(5)

            InsertTextFieldAuth(
                uiState.statePass,
                onValueChange = { viewModel.changePass(it) },
                placeholder = "لطفا رمز خود را وارد نمایید",
                visualTransformation = visibleEye,
                trailingIcon = {
                    IconButton({ visibleEye = !visibleEye }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_hide_and_show_pass),
                            contentDescription = null,
                        )
                    }
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "حساب کاربری ندارید؟",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.Register) {
                                popUpTo(
                                    Screens.Login
                                ) { inclusive = true }
                            }
                        },
                )
                SpacerWith(10)

                Text(
                    "رمز عبور خود را فراموش کردید؟",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.FoundEmail)
                        },
                )
            }
            SpacerHeight(20)

            InsertButton(
                "تایید و ادامه",
                viewModel.enabledChange(uiState.stateEmail, uiState.statePass)
            ) {
                viewModel.loginUser(
                    uiState.stateEmail,
                    uiState.statePass
                )

            }


        }
    }

    when (uiState.loginStatus) {

        LoginStatus.IDLE -> {}

        LoginStatus.LOADING -> {

            InsertDialog({ viewModel.onDismissDialog() }, "درحال بررسی")

        }

        LoginStatus.SUCCESS -> {
            navController.navigate(Screens.HomeScreen){
                popUpTo(Screens.Login) {inclusive = true  }
            }
        }
        LoginStatus.ERROR -> {}


    }


}



