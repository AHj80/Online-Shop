package com.ahj.onlineshop.feature.product.presentation.listProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ahj.onlineshop.core.common.ui.component.Progress
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.authFeature.DrawCircleBackground
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTextFieldAuth
import com.ahj.onlineshop.core.common.ui.component.authFeature.InsertTitle
import com.ahj.onlineshop.core.common.ui.component.productFeature.ProductItemSample
import com.ahj.onlineshop.core.common.ui.component.productFeature.TopCategory
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@Composable
fun ListProductScreen(
    navController: NavController,
    subCategoryType: String,
    parentCategory: String,
    viewModel: ListProductViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categoryTitle = uiState.category.find { it.categoryType == parentCategory }
    val subTitle = uiState.subCategory.find { it.categoryType == uiState.selected }


    LaunchedEffect(Unit) {
        viewModel.checkCategory(subCategoryType , parentCategory)
    }

    DrawCircleBackground {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items(uiState.subCategory.size) {
                    val data = uiState.subCategory[it]
                    val selected = data.categoryType == uiState.selected
                    TopCategory(data, selected) {
                        viewModel.selectedItem(data.categoryType , parentCategory)
                    }
                }
            }

            SpacerHeight(20)



            when (uiState.status) {
                ListProductStatus.LOADING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Progress(25)
                    }

                }

                ListProductStatus.ERROR -> {
                    ErrorRefreshing("خطا در برقراری ارتباط") {
                        viewModel.checkCategory(uiState.selected, parentCategory)
                    }
                }

                else -> {
                    categoryTitle?.let {
                        InsertTitle(it.title)
                    }
                    SpacerHeight(20)

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

                    subTitle?.let {
                        InsertTitle(it.title)
                    }
                    SpacerHeight(
                        10
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        items(uiState.product.size) {
                            ProductItemSample(uiState.product[it]) {
                                navController.navigate(
                                    Screens.DetailProduct(
                                        uiState.product[it].id,
                                        uiState.product[it].categoryType
                                    )
                                )
                            }
                        }
                    }

                }

            }
        }

    }
}