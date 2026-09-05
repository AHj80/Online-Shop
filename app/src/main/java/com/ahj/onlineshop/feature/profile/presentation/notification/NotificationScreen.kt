package com.ahj.onlineshop.feature.profile.presentation.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini


@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground {

        Column {

            CustomAnimate(uiState.header != null , 200 , 200) {
                uiState.header?.profile?.let {

                    TopAppProfileMini(uiState.header?.avatar, it)
                }
            }

            when (uiState.status) {
                NotificationStatus.EMPTY -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        uiState.message?.let {
                            Text(
                                it,
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Gray
                            )
                        }

                    }
                }

                NotificationStatus.LOADING -> {}
                NotificationStatus.SUCCESS -> {}
                NotificationStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) {
                        viewModel.getAllData()
                    }
                }
            }

        }
    }

}