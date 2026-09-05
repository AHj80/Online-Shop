package com.ahj.onlineshop.feature.product.presentation.subCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.ahj.onlineshop.core.common.ui.component.StatusCustomAnimated
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.product.component.InsertCategoryGrid
import com.ahj.onlineshop.feature.product.component.ProductItemSample
import com.ahj.onlineshop.feature.product.component.SearchProduct
import com.ahj.onlineshop.feature.product.component.ShowAll
import com.ahj.onlineshop.feature.product.component.ShowBestSell
import com.ahj.onlineshop.feature.product.component.TopCategory
import com.ahj.onlineshop.feature.product.domain.model.ProductModel


@Composable
fun SubCategoryScreen(
    navController: NavController,
    viewModel: SubCategoriesViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val title = uiState.categories.find { it.categoryType == uiState.selected }


    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            showSnackBar(it)
        }
        viewModel.resetSnackBar()
    }




    DrawCircleBackground(false) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
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

            StatusCustomAnimated(uiState.status) { status ->
                when (status) {
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
                        ErrorRefreshing(uiState.messageStatus) {
                            viewModel.saveStateData()
                        }
                    }

                    else -> {
                        SuccessContent(
                            navController,
                            title?.title,
                            uiState,
                            { viewModel.updateText(it) },
                            { viewModel.changeModalState(it) },
                            { viewModel.addToCart(it) },
                            { product ->
                                navController.navigate(
                                    Screens.DetailProduct(product.id, product.categoryType)
                                )
                            },
                            { categoryType ->
                                navController.navigate(
                                    Screens.ListProductScreen(
                                        categoryType,
                                        uiState.selected,
                                    )
                                )
                            }
                        )
                    }

                }
            }
        }


    }


}


@Composable
private fun SuccessContent(
    navController: NavController,
    title: String?,
    uiState: SubCategoriesUiState,
    onSearchTextChanged: (String) -> Unit,
    onModalStateChanged: (Boolean) -> Unit,
    onAddToCart: (ProductModel) -> Unit,
    onProductClick: (ProductModel) -> Unit,
    onSubCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        title?.let { text ->
            InsertTitle("پوشاک $text")
        }
        SpacerHeight(20)
        InsertTextFieldAuth(
            value = uiState.stateText,
            onValueChange = { onSearchTextChanged(it) },
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

        SearchProduct(uiState.product, uiState.stateText) { product ->

            onProductClick(product)
        }

        InsertCategoryGrid(uiState.subCategories) { categoryType ->
            onSubCategoryClick(categoryType.categoryType)
        }
        SpacerHeight(20)

        Spacer(Modifier.weight(1f))
        ShowAll("پرفروش ترین ها") {
            onModalStateChanged(true)
        }
        if (uiState.showModal)
            ShowBestSell(
                data = uiState.product,
                { state -> onModalStateChanged(state) },
                { currentProduct ->
                    navController.navigate(
                        Screens.DetailProduct(
                            currentProduct.id,
                            currentProduct.categoryType
                        )
                    )
                }
            ) {
                onAddToCart(it)
            }
        SpacerHeight(20)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(uiState.product.size) {
                ProductItemSample(
                    productModel = uiState.product[it],
                    onClick = {
                        navController.navigate(
                            Screens.DetailProduct(
                                uiState.product[it].id,
                                uiState.product[it].categoryType
                            )
                        )
                    }
                ) {
                    onAddToCart(uiState.product[it])
                }
            }
        }
    }
}

