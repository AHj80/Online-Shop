package com.ahj.onlineshop.feature.authentication.presentation.foundEmail

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.permissionManager.PermissionStatus
import com.ahj.onlineshop.core.common.permissionManager.PermissionViewModel
import com.ahj.onlineshop.core.common.ui.component.InsertButton
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.authFeature.DrawCircleBackground
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertBody
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTextFieldAuth
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTitle


@Composable
fun FoundEmailScreen(
    navController: NavController,
    viewModel: FoundEmailViewModel = hiltViewModel(),
    permissionViewModel: PermissionViewModel = hiltViewModel()
) {

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) permissionViewModel.checkPermission(android.Manifest.permission.POST_NOTIFICATIONS)

    }

    LaunchedEffect(Unit) {
        permissionViewModel.checkPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    val statePermission by permissionViewModel.permissionStatus.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DrawCircleBackground(uiState.foundEmailStatus == FoundEmailStatus.LOADING) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .blur(if (uiState.foundEmailStatus == FoundEmailStatus.LOADING) 10.dp else 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InsertBody("لطفا ابتدا ایمیلی که با آن حساب کاربری خود را ساخته بودید وارد نمایید\nقبل از انجام این مرحله الزامیست ابتدا بعد از زدن دکمه ادامه ، مجوز دسترسی به اعلان ها را برای دریافت کد تایید فعال و مجددا امتحان بفرمایید")
            SpacerHeight(20)

            InsertTitle("ایمیل")
            SpacerHeight(5)
            InsertTextFieldAuth(
                uiState.stateEmail,
                { viewModel.updateEmail(it) },
                placeholder = "ایمیل خود را وارد نمایید",
                KeyboardType.Email,
                supportingText = uiState.message,
                isError = uiState.isError
            )
            SpacerHeight(30)

            InsertButton(
                "تایید",
                enabled = viewModel.checking(uiState.stateEmail)
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && statePermission == PermissionStatus.NEED_REQUEST)
                    launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                else
                    viewModel.foundEmail(uiState.stateEmail)
            }


        }
    }
    when (uiState.foundEmailStatus) {

        FoundEmailStatus.LOADING -> {
            InsertDialog({ viewModel.onDismissDialog() }, "در حال بررسی")
        }

        FoundEmailStatus.SUCCESS -> {
            val id = uiState.loginModel?.id
            id?.let {
                navController.navigate(Screens.EmailOTP(it)) {
                    launchSingleTop = true
                }
            }
            viewModel.resetScreenStatus()

        }

        FoundEmailStatus.ERROR -> {}

        FoundEmailStatus.IDLE -> {}

    }
}