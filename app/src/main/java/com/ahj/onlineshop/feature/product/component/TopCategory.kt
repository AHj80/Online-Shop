package com.ahj.onlineshop.feature.product.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun TopCategory(
    categoryData: CategoryData,
    isSelected: Boolean,
    clickable: () -> Unit
) {

    val animateSize by animateDpAsState(
        if (isSelected) 70.dp else 50.dp,
        animationSpec = tween(200)
    )

    val imageSize by animateDpAsState(
        targetValue = if (isSelected) 50.dp else 30.dp,
        animationSpec = tween(200)
    )
    val animateColor by animateColorAsState(
        if (isSelected) Color.Red else Color.Transparent,
        tween(500)
    )

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .size(animateSize)
            .padding(horizontal = 3.dp)
            .clickable { clickable() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = if (isSelected) BorderStroke(2.dp, animateColor) else null
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(categoryData.image),
                null,
                modifier = Modifier
                    .size(imageSize)
            )
        }
    }

}