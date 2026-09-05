package com.ahj.onlineshop.feature.product.presentation.detailProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil3.compose.AsyncImage
import com.ahj.onlineshop.R
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.StatusCustomAnimated
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.formatPriceToPersian
import com.ahj.onlineshop.core.common.utils.shareText
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.product.component.AddToCart
import com.ahj.onlineshop.feature.product.component.TabProductItem


@Composable
fun DetailProductScreen(
    viewModel: DetailProductViewModel = hiltViewModel(),
    id: String,
    categoryType: String,
    navController: NavController,
    showSnackBar: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(id, categoryType) {
        viewModel.getProduct(id, categoryType)
    }

    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            showSnackBar(it)
        }
        viewModel.resetMessage()
    }



    Scaffold(
        bottomBar = {
            StatusCustomAnimated(uiState.status) {
                if (it == DetailProductStatus.SUCCESS) {
                    AddToCart(
                        uiState.product.price.formatPriceToPersian(),
                        uiState.product.finalPrice.formatPriceToPersian(),
                        discount = uiState.product.discount,
                        statusButton = uiState.inCart,
                        addOnClick = {
                            viewModel.addToCart(uiState.product)
                        }
                    ) {

                        navController.navigate(Screens.Cart) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        },
        modifier = Modifier.imePadding()
    ) { innerPadding ->


        DrawCircleBackground(
            uiState.status == DetailProductStatus.LOADING
        ) {

            when (uiState.status) {
                DetailProductStatus.LOADING -> {
                    InsertDialog(text = "در حال ارتباط")
                }

                DetailProductStatus.ERROR -> {
                    ErrorRefreshing(uiState.messageStatus) {
                        viewModel.getProduct(
                            id,
                            categoryType
                        )
                    }
                }

                else -> {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                            .padding(30.dp),
                        colors = CardDefaults.cardColors(containerColor = BackgroundCardColor),
                        shape = RoundedCornerShape(30.dp),
                        elevation = CardDefaults.elevatedCardElevation(1.dp)
                    ) {


                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TopDetail(uiState)

                            BottomDetail(
                                uiState,
                                onEvent = { event ->
                                    if (event is DetailProductUiEvent.OnSimilarClick) {
                                        navController.navigate(
                                            Screens.DetailProduct(
                                                event.id,
                                                event.categoryType
                                            )
                                        )
                                    } else {
                                        viewModel.onEvent(event)
                                    }
                                }
                            )

                        }
                    }

                }

            }

        }
    }

}


@Composable
private fun TopDetail(
    uiState: DetailProductUiState,

    ) {

    val statePager = rememberPagerState { uiState.product.image.size }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
    ) {

        HorizontalPager(
            state = statePager
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    model = uiState.product.image[it],
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.loading_coil)
                )

            }
        }
        if (uiState.product.discount != 0)
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier

                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    ButtonColor_One,
                                    ButtonColor_Tow
                                )
                            ),
                            shape = RoundedCornerShape(
                                bottomEnd = 30.dp,
                                topEnd = 30.dp
                            )
                        )

                ) {

                    Text(
                        "${uiState.product.discount.toPersianDigit()}%",
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 5.dp
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        repeat(uiState.product.image.size) {
            val color =
                if (statePager.currentPage == it) ButtonColor_Tow else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(7.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}


@Composable
private fun BottomDetail(
    uiState: DetailProductUiState,
    onEvent: (DetailProductUiEvent) -> Unit
) {

    val listTab = listOf(
        "توضیحات",
        "ویژگی ها",
        "نظرات",
        "محصولات مشابه"
    )


    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd

    ) {
        Card(
            Modifier.height(50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                bottomStart = 0.dp,
                topEnd = 20.dp
            )
        ) {
            Row(
                modifier = Modifier.padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                IconButton({

                    onEvent(
                        DetailProductUiEvent.FavoriteOnClick(
                            uiState.isFavorite,
                            uiState.product.id.toInt(),
                            uiState.product.title,
                            uiState.product.image[0],
                            uiState.product.categoryType,
                        )
                    )
                }) {

                    CustomAnimate(uiState.isFavorite) {

                        Icon(
                            Icons.Filled.BookmarkRemove,
                            null,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    CustomAnimate(!uiState.isFavorite) {

                        Icon(
                            Icons.Outlined.BookmarkAdd,
                            null,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                }




                Text(
                    uiState.product.rating.toPersianDigit(),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Icon(
                    Icons.Default.Star,
                    null,
                    tint = Color(0XFFFFB800)
                )
                IconButton({
                    context.shareText("\nخرید لباس : ${uiState.product.title} همراه با لباس ها دیگر با تخفیف در اپلیکیشن آنلاین شاپ  \n برای نصب اپلیکیشن روی لینک زیر کلیک نمایید : \n")
                }) {
                    Icon(
                        Icons.Outlined.Share, null
                    )
                }

            }
        }
    }

    Box {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.height(300.dp),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    uiState.product.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(20.dp)
                )


                TabProductItem(
                    listTab,
                    uiState.product,
                    selected = uiState.selectedTab,
                    similarProduct = uiState.similarProduct,
                    tabSelection = {
                        onEvent(
                            DetailProductUiEvent.OnTabChange(it)
                        )
                    },
                    similarOnClick = { currentProduct ->
                        onEvent(
                            DetailProductUiEvent.OnSimilarClick(
                                currentProduct.id,
                                currentProduct.categoryType
                            )
                        )
                    },
                    stateTextComment = uiState.stateTextComment,
                    stateTextChange = {
                        onEvent(
                            DetailProductUiEvent.OnCommentChange(it)
                        )
                    },
                    rate = {
                        onEvent(
                            DetailProductUiEvent.OnRateChange(it)
                        )
                    },
                    sendComment = {

                        onEvent(
                            DetailProductUiEvent.OnSendComment(
                                uiState.product.id.toInt(),
                                uiState.product.title,
                                uiState.product.image[0],
                                uiState.stateTextComment,
                                uiState.rate
                            )
                        )
                    },
                    addOnClick = {

                        onEvent(
                            DetailProductUiEvent.OnAddToCart(
                                it
                            )
                        )

                    }
                )


            }

        }

    }
}