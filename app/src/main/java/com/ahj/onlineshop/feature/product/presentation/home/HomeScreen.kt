package com.ahj.onlineshop.feature.product.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.R
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.product.component.CategoriesSample
import com.ahj.onlineshop.feature.product.component.InsertBanner
import com.ahj.onlineshop.feature.product.component.ProductItemSample
import com.ahj.onlineshop.feature.product.component.SearchProduct
import com.ahj.onlineshop.feature.product.component.ShowAll
import com.ahj.onlineshop.feature.product.component.ShowBestSell
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState { uiState.banner.size }

    val resultSearch =
        uiState.data.filter { it.title.contains(uiState.stateSearch, ignoreCase = true) }


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
                            value = uiState.stateSearch,
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
                        SearchProduct(resultSearch, uiState.stateSearch) { index ->
                            val currentProduct = resultSearch[index]
                            navController.navigate(
                                Screens.DetailProduct(
                                    currentProduct.id,
                                    currentProduct.categoryType
                                )
                            )
                        }

                        SpacerHeight(10)

                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            items(uiState.categories.size) {
                                CategoriesSample(uiState.categories[it]) {
                                    navController.navigate(
                                        Screens.SubCategory(
                                            uiState.categories[it].categoryType
                                        )
                                    )
                                }
                            }
                        }
                        SpacerHeight(40)
                    }

                    item {
                        ShowAll("پرفروش ترین ها") {
                            viewModel.changeModalState(true)
                        }
                        if (uiState.showModal)
                            ShowBestSell(
                                uiState.data,
                                { stateVisible -> viewModel.changeModalState(stateVisible) },
                                { currentProduct ->
                                    viewModel.changeModalState(false)
                                    navController.navigate(
                                        Screens.DetailProduct(
                                            currentProduct.id,
                                            currentProduct.categoryType
                                        )
                                    )
                                }
                            ){ currentProduct ->
                                viewModel.addToCart(currentProduct)
                            }

                        SpacerHeight(10)
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val limitedItems = uiState.data.take(6)
                            items(limitedItems.size) {
                                ProductItemSample(
                                    uiState.data[it],
                                    onClick = {
                                        navController.navigate(
                                            Screens.DetailProduct(
                                                uiState.data[it].id,
                                                uiState.data[it].categoryType
                                            )
                                        )
                                    }
                                ) {
                                    viewModel.addToCart(uiState.data[it])
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




