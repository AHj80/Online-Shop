package com.ahj.onlineshop.feature.profile.presentation.userProfile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.profile.component.TopAppProfileFull
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.feature.profile.data.local.offlineData.OfflineData
import com.ahj.onlineshop.feature.profile.domain.model.CategoryProfile


@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            viewModel.saveNewAvatar(it)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCircleColor),

        ) {

        TopAppProfileFull(
            uiState.profile,
            image = uiState.avatar
        ) {
            photoPickerLauncher.launch("image/*")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ) {
            val data = OfflineData.listData
            items(data.size) {
                CategorySample(data[it])
            }
        }

    }

}

@Composable
fun CategorySample(categoryProfile: CategoryProfile) {


    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {},
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                BackgroundCircleColor,
                                Color.White
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(categoryProfile.image),
                    null,
                    modifier = Modifier
                        .padding(25.dp)
                        .size(70.dp)

                )

            }

            Text(
                categoryProfile.name,
                style = MaterialTheme.typography.titleSmall
            )
            SpacerHeight(10)
        }
    }
}

