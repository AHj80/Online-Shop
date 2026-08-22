package com.ahj.onlineshop.feature.profile.presentation.favorites

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.ErrorRefreshing
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.authentication.component.DrawCircleBackground
import com.ahj.onlineshop.feature.authentication.component.InsertTitle
import com.ahj.onlineshop.feature.profile.component.FavoriteItemSample
import com.ahj.onlineshop.feature.profile.component.TopAppProfileMini


@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DrawCircleBackground {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomAnimate(uiState.profile != null , 50 ,50) {

                uiState.profile?.let {
                    TopAppProfileMini(uiState.avatar, it)
                }
            }
            when(uiState.status){
                FavoriteStatus.EMPTY -> {
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
                FavoriteStatus.LOADING -> {
                    InsertDialog({} , "در حال برقراری ارتباط")
                }
                FavoriteStatus.SUCCESS -> {

                    SpacerHeight(30)

                    InsertTitle("علاقه مندی های من")
                    SpacerHeight(10)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        items(uiState.data.size, key = { uiState.data[it].id }) {
                            FavoriteItemSample(
                                uiState.data[it],
                                modifier = Modifier.animateItem(
                                    fadeInSpec = tween(600),
                                    fadeOutSpec = tween(600),
                                    placementSpec = spring(stiffness = Spring.StiffnessMedium)
                                ),
                                {
                                    navController.navigate(
                                        Screens.DetailProduct(
                                            uiState.data[it].id.toString(),
                                            uiState.data[it].categoryType
                                        )
                                    )
                                }) {
                                viewModel.deletedFavorite(uiState.data[it])
                            }
                        }
                    }
                }
                FavoriteStatus.ERROR -> {
                    ErrorRefreshing(uiState.message){
                        viewModel.loadData()
                    }
                }
            }
        }
    }

}