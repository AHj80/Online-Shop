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
import com.ahj.onlineshop.core.common.ui.component.StatusCustomAnimated
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.product.component.ProductItemSample
import com.ahj.onlineshop.feature.product.component.SearchProduct
import com.ahj.onlineshop.feature.product.component.TopCategory
import com.ahj.onlineshop.feature.product.domain.model.ProductModel


@Composable
fun ListProductScreen(
    navController: NavController,
    viewModel: ListProductViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categoryTitle = uiState.category.find { it.categoryType == uiState.parentCategory }
    val subTitle = uiState.subCategory.find { it.categoryType == uiState.selected }


    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            showSnackBar(it)
            viewModel.resetSnackBar()
        }
    }

    DrawCircleBackground {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            HeaderTabCategory(
                uiState,
                categoryChange = {
                    viewModel.selectedItem(it , uiState.parentCategory)
                }
            )

            SpacerHeight(20)



            StatusCustomAnimated(uiState.status) { status ->
                when (status) {
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
                            viewModel.saveStateData()
                        }
                    }

                    else -> {
                        SuccessContent(
                            categoryTitle?.title,
                            uiState,
                            subTitle?.title,
                            { viewModel.updateText(it) },
                            {
                                navController.navigate(
                                    Screens.DetailProduct(
                                        it.id,
                                        it.categoryType
                                    )
                                )
                            },
                            {
                                navController.navigate(
                                    Screens.DetailProduct(
                                        it.id,
                                        it.categoryType
                                    )
                                )
                            },
                            { viewModel.addToCart(it) }
                        )
                    }

                }
            }
        }

    }
}

@Composable
private fun SuccessContent(
    categoryTitle: String?,
    uiState: ListProductUiState,
    subTitle: String?,
    updateText: (String) -> Unit,
    navigatingSearch: (ProductModel) -> Unit,
    productNavigating: (ProductModel) -> Unit,
    addOnClick: (ProductModel) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {


        categoryTitle?.let {
            InsertTitle(it)
        }
        SpacerHeight(20)

        InsertTextFieldAuth(
            value = uiState.stateSearch,
            onValueChange = { updateText(it) },
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

        SearchProduct(uiState.product, uiState.stateSearch) { product ->
            navigatingSearch(product)
        }
        SpacerHeight(10)

        subTitle?.let {
            InsertTitle(it)
        }

        SpacerHeight(
            10
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            items(uiState.product.size) {
                ProductItemSample(
                    productModel = uiState.product[it],
                    onClick = {
                        productNavigating(uiState.product[it])
                    }
                ) {
                    addOnClick(uiState.product[it])
                }
            }
        }
    }

}


@Composable
private fun HeaderTabCategory(
    uiState: ListProductUiState,
    categoryChange: (String) -> Unit
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
                categoryChange(data.categoryType)
            }
        }
    }
}