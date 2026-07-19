package com.ahj.onlineshop.feature.product.presentation.subCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ahj.onlineshop.core.common.ui.component.productFeature.InsertCategoryGrid
import com.ahj.onlineshop.core.common.ui.component.productFeature.ProductItemSample
import com.ahj.onlineshop.core.common.ui.component.productFeature.ShowAll
import com.ahj.onlineshop.core.common.ui.component.productFeature.ShowBestSell
import com.ahj.onlineshop.core.common.ui.component.productFeature.TopCategory
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@Composable
fun SubCategoryScreen(
    navController: NavController,
    viewModel: SubCategoriesViewModel = hiltViewModel(),
    parentCategory: String
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val title = uiState.categories.find { it.categoryType == uiState.selected }




    LaunchedEffect(parentCategory) {
        viewModel.checkCategory(parentCategory)
    }

    DrawCircleBackground(false) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                items(uiState.categories.size) {
                    val data = uiState.categories[it]
                    val selected = data.categoryType == uiState.selected
                    TopCategory(data, selected) {
                        viewModel.selectedCategory(data.categoryType)
                    }
                }
            }
            SpacerHeight(20)






            when (uiState.status) {
                SubCategoriesStatus.LOADING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Progress(25)
                    }
                }

                SubCategoriesStatus.ERROR -> {
                    ErrorRefreshing(uiState.message) {
                        viewModel.selectedCategory(
                            uiState.selected,
                        )
                    }
                }

                else -> {
                    title?.let {
                        InsertTitle("پوشاک ${it.title}")
                    }
                    SpacerHeight(20)
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

                    InsertCategoryGrid(uiState.subCategories) { categoryType ->
                        navController.navigate(
                            Screens.ListProductScreen(
                                categoryType.categoryType,
                                uiState.selected,
                            )
                        )
                    }
                    SpacerHeight(20)

                    ShowAll("پرفروش ترین ها") {
                        viewModel.changeModalState(true)
                    }
                    if (uiState.showModal)
                        ShowBestSell(
                            data = uiState.product,
                            { state -> viewModel.changeModalState(state) },
                            { currentProduct->
                                navController.navigate(
                                    Screens.DetailProduct(
                                        currentProduct.id,
                                        currentProduct.categoryType
                                    )
                                )
                            }
                        )
                    SpacerHeight(20)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
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


