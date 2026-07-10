package com.ahj.onlineshop.feature.product.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.component.authFeature.DrawCircleBackground
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTextFieldAuth
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTitle
import com.ahj.onlineshop.core.common.ui.component.productFeature.CategoriesSample
import com.ahj.onlineshop.core.common.ui.component.productFeature.InsertBanner
import com.ahj.onlineshop.core.common.ui.component.productFeature.ShowAll
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.toPersianDigit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState { uiState.banner.size }


    DrawCircleBackground(
        blur = uiState.status == HomeScreenStatus.LOADING
    ) {

        when (uiState.status) {

            HomeScreenStatus.LOADING -> {
                InsertDialog({}, "در حال برقراری ارتباط")
            }

            HomeScreenStatus.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {
                        InsertBanner(pagerState, uiState.banner)

                        SpacerHeight(20)
                    }
                    item {
                        InsertTitle("تنها با یک کلیک خرید کن!")
                        SpacerHeight(10)
                        InsertTextFieldAuth(
                            value = uiState.stateText,
                            onValueChange = { viewModel.updateText(it) },
                            placeholder = "لباست رو جست و جو کن...",
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.search_normal),
                                    null,
                                    modifier = Modifier.size(20.dp),
                                    tint = ButtonColor_Tow
                                )
                            }
                        )
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            items(uiState.categories.size) {
                                CategoriesSample(uiState.categories[it])
                            }
                        }
                        SpacerHeight(40)
                    }

                    item {
                        ShowAll("پرفروش ترین ها")
                    }

                    item {
                        Card(
                            colors = CardDefaults.cardColors(BackgroundCardColor),
                            shape = RoundedCornerShape(23.dp),
                            elevation = CardDefaults.elevatedCardElevation(3.dp),
                            modifier = Modifier
                                .padding(20.dp)
                                .width(195.dp)
                                .height(250.dp)
                                .padding()

                        ) {

                            Box {
                                Box(
                                    contentAlignment = Alignment.TopCenter,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp)
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.image_one),
                                        contentDescription = null,
                                        modifier = Modifier.size(130.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Box(
                                    contentAlignment = Alignment.TopStart,
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                        .background(
                                            Brush.horizontalGradient(
                                                listOf(
                                                    ButtonColor_One,
                                                    ButtonColor_Tow
                                                )
                                            ),
                                            shape = RoundedCornerShape(
                                                bottomEnd = 21.dp,
                                                topEnd = 21.dp
                                            )
                                        )

                                ) {

                                    Text(
                                        "10%",
                                        modifier = Modifier.padding(5.dp),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }

                                Box(
                                    contentAlignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 10.dp)
                                ) {
                                    Box {
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(90.dp)
                                                .padding(horizontal = 15.dp),
                                            colors = CardDefaults.cardColors(Color.White),
                                            elevation = CardDefaults.elevatedCardElevation(4.dp)
                                        ) {
                                            Text(
                                                "پیراهن مردانه لی جنس عالی سایز مدیوم",
                                                style = MaterialTheme.typography.titleSmall,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(
                                                        bottom = 10.dp,
                                                        start = 7.dp,
                                                        end = 7.dp
                                                    ),
                                                contentAlignment = Alignment.BottomCenter
                                            ) {
                                                Box(
                                                    modifier = Modifier.fillMaxWidth()
                                                ) {

                                                    Box(
                                                        contentAlignment = Alignment.BottomStart,
                                                        modifier = Modifier.fillMaxWidth()
                                                    ) {
                                                        Text(
                                                            "2,000,000".toPersianDigit(),
                                                            style = MaterialTheme.typography.titleSmall,
                                                            fontSize = 9.sp,
                                                            color = Color.DarkGray
                                                        )
                                                        HorizontalDivider(
                                                            thickness = 1.5.dp,
                                                            modifier = Modifier
                                                                .width(45.dp)
                                                                .rotate(-5f)
                                                                .padding(bottom = 5.dp)
                                                        )
                                                    }


                                                    SpacerWith(10)
                                                    Box(
                                                        contentAlignment = Alignment.BottomEnd,
                                                        modifier = Modifier.fillMaxWidth()
                                                    ) {
                                                        Text(
                                                            "3,000,000 تومان".toPersianDigit(),
                                                            style = MaterialTheme.typography.titleSmall,
                                                            fontSize = 11.sp
                                                        )
                                                    }


                                                }
                                            }
                                        }
                                    }
                                }


                                Box(
                                    contentAlignment = Alignment.BottomEnd,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = 35.dp, end = 3.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                Brush.horizontalGradient(
                                                    listOf(
                                                        ButtonColor_One,
                                                        ButtonColor_Tow
                                                    )
                                                ),
                                                shape = CircleShape
                                            )
                                            .size(30.dp)
                                            .clickable {},
                                        contentAlignment = Alignment.Center

                                    ) {
                                        Text("+", color = Color.White, fontSize = 20.sp)
                                    }
                                }
                            }
                        }
                    }


                }
            }

            else -> {
                ErrorRefreshing(uiState.message) {
                    viewModel.getHomeData()
                }
            }
        }

    }


}


