package com.ahj.onlineshop.feature.product.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import kotlinx.coroutines.delay


@Composable
fun InsertBanner(pager: PagerState, image: List<BannerModel>) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        LaunchedEffect(image.size) {

            while (true) {
                delay(6000)

                if (!pager.isScrollInProgress) {
                    val nextPage =
                        if (pager.currentPage < image.size - 1) {

                            pager.currentPage + 1
                        } else {
                            0
                        }
                    pager.animateScrollToPage(nextPage, animationSpec = tween(1000))
                }
            }
        }

        Box {
            HorizontalPager(
                pager,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            ) {
                Box {

                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .height(130.dp)
                    ) {
                        Image(
                            painter = painterResource(image[it].image),
                            null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )

                    }

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp),
                contentAlignment = Alignment.Center
            ) {


                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(color = Color.Gray, shape = CircleShape).padding(3.dp)
                    ) {
                        repeat(image.size) {

                            val color =
                                if (pager.currentPage == it) Color.White else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 3.dp)
                                    .size(7.dp)
                                    .background(color, shape = CircleShape)
                            )

                        }
                    }
                }

            }

        }
    }

}