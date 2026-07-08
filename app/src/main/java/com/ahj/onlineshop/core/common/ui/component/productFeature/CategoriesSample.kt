package com.ahj.onlineshop.core.common.ui.component.productFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight

@Composable
fun CategoriesSample(data: CategoryData, onClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(100.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {onClick()},

        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(data.image),
                contentDescription = data.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
            )
            SpacerHeight(7)
            Text(
                data.title,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}