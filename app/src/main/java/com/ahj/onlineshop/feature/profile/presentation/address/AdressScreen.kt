package com.ahj.onlineshop.feature.profile.presentation.address

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground


@Composable
fun AddressScreen(
    viewModel: AddressViewModel = hiltViewModel()
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DrawCircleBackground() {

    }


}

@Composable
fun AddressItem(
    readonly: Boolean,
    selectedDefault: Boolean,

    selectedDefaultOnClick: () -> Unit
) {

}

