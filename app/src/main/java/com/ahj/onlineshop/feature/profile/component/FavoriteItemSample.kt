package com.ahj.onlineshop.feature.profile.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel


@Composable
fun FavoriteItemSample(
    favorite: FavoriteModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit ,
    removeOnClick: () -> Unit
) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .clickable { onClick() }
            .padding(5.dp)
            .width(195.dp)
            .height(220.dp),

        colors = CardDefaults.cardColors(BackgroundCardColor)


    ) {

        Box {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                AsyncImage(
                    model = favorite.image,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.loading_coil)
                )
            }

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .padding(top = 20.dp)


                ) {
                    IconButton({removeOnClick()}) {
                        Icon(
                            Icons.Default.BookmarkRemove,null
                        )
                    }

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
                            .padding(horizontal = 10.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                favorite.title,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

        }
    }
}
