package com.ahj.onlineshop.feature.profile.presentation.userExperience

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini


@Composable
fun UserExperiencesScreen(
    viewModel: UserExperienceViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    DrawCircleBackground {

        Column(modifier = Modifier.fillMaxWidth()) {

            CustomAnimate(uiState.header?.profile != null, enter = 200, exit = 200) {
                uiState.header?.profile?.let {
                    TopAppProfileMini(uiState.header?.avatar, it)
                }
            }
            SpacerHeight(20)

            when (uiState.status) {
                UserExperienceStatus.EMPTY -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        uiState.message?.let {
                            Text(
                                it,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                UserExperienceStatus.LOADING -> {
                    InsertDialog({}, "در حال برقراری ارتباط")
                }

                UserExperienceStatus.SUCCESS -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {


                        item {
                            InsertTitle("تجربه های خرید من")
                            SpacerHeight(20)
                        }

                        items(uiState.experienceData.size) {
                            UserExperienceItem(uiState.experienceData[it])

                        }


                    }
                }

                UserExperienceStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) {
                        viewModel.getAllData()
                    }
                }
            }
        }
    }


}

@Composable
fun UserExperienceItem(
    experienceModel: UserExperienceModel
) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier

                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier

                            .padding(horizontal = 5.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = BackgroundCardColor)
                    ) {
                        AsyncImage(
                            model = experienceModel.image,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        experienceModel.title,
                        style = MaterialTheme.typography.titleSmall
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            experienceModel.rate.toPersianDigit(),
                            style = MaterialTheme.typography.titleSmall,

                            )
                        SpacerWith(10)
                        Icon(
                            Icons.Default.Star,
                            null,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .size(20.dp),
                            tint = Color(0XFFFFB800)
                        )
                    }
                }


            }

            Text(
                experienceModel.comment,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            )

        }
    }


}
