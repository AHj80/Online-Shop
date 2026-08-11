package com.ahj.onlineshop.feature.authentication.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertLogo
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.authentication.component.CustomAlertDialog

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()

    ) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isEnabled by remember {
        derivedStateOf {
            viewModel.validating(
                uiState.stateEmail,
                uiState.statePass,
                uiState.statePassConfirm
            )
        }
    }
    var visibleEye by remember { mutableStateOf(false) }

    DrawCircleBackground(
        uiState.registerStatus == RegisterStatus.LOADING || uiState.registerStatus ==RegisterStatus.ERROR
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .blur(if (uiState.registerStatus == RegisterStatus.LOADING) 10.dp else 0.dp ),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerHeight(50)

            InsertLogo()
            SpacerHeight(20)

            InsertTitle("ایمیل")
            SpacerHeight(5)

            InsertTextFieldAuth(
                value = uiState.stateEmail,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = "لطفا ایمیل خود را وارد نمایید",
                keyboardType = KeyboardType.Email,
                supportingText = uiState.message,
                isError = uiState.registerStatus == RegisterStatus.ERROR
            )

            InsertTitle("رمز عبور")
            SpacerHeight(5)

            InsertTextFieldAuth(
                value = uiState.statePass,
                onValueChange = { viewModel.updatePass(it) },
                placeholder = "لطفا رمزعبور خود را وارد نمایید",
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
            InsertTitle("تکرار رمز عبور")
            SpacerHeight(5)

            InsertTextFieldAuth(
                value = uiState.statePassConfirm,
                onValueChange = { viewModel.updatePassConfirm(it) },
                placeholder = "رمز عبور خود را تکرار کنید",
                visualTransformation = visibleEye
                )

            Text(
                "حساب کاربری دارید؟",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 10.sp,
                modifier = Modifier
                    .clickable { navController.navigate(Screens.Login){
                        popUpTo (
                            Screens.Register
                        ){ inclusive = true }
                    } },
            )
            SpacerHeight(30)

            InsertButtonPrimary(
                text = "ثبت نام",
                enabled = isEnabled
            ) {
                viewModel.sendRegisterData(
                    uiState.stateEmail,
                    uiState.statePass,
                )
            }


        }

        when(uiState.registerStatus){

            RegisterStatus.LOADING ->{
                InsertDialog( text = "درحال بررسی")
            }
            RegisterStatus.SUCCESS ->  {
                navController.navigate(Screens.Login){
                    popUpTo(Screens.Login){
                        inclusive = true
                    }
                }
            }
            RegisterStatus.ERROR -> {
                if (uiState.showDialog)
                    CustomAlertDialog("ثبت نام ناموفق بود"){
                        viewModel.onDismissAlertDialog()
                    }
            }
            RegisterStatus.IDLE -> {}
        }
    }
}