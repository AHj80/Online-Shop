package com.ahj.onlineshop.feature.profile.presentation.userProfile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.feature.profile.component.CategorySample
import com.ahj.onlineshop.feature.profile.component.navigatingUserProfile
import com.ahj.onlineshop.feature.profile.component.TopAppProfileFull
import com.ahj.onlineshop.feature.profile.data.local.offlineData.OfflineData


@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            viewModel.saveNewAvatar(context, it)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCircleColor),

        ) {

        when (uiState.status) {

            UserProfileStatus.SUCCESS -> {
                TopAppProfileFull(
                    uiState.profile,
                    image = uiState.avatar,
                    editOnClick = {
                        navController.navigate(Screens.EditProfile)
                    }
                ) {
                    photoPickerLauncher.launch("image/*")
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {

                    items(OfflineData.listData.size) {
                        CategorySample(OfflineData.listData[it]) {
                            navigatingUserProfile(
                                navController,
                                it,
                            )
                        }
                    }

                }
            }

            UserProfileStatus.LOADING -> {
                InsertDialog({}, "در حال بررسی")
            }

            else -> {
                ErrorRefreshing(uiState.message) {
                    viewModel.observeSessionData()
                }

            }
        }

    }

}



