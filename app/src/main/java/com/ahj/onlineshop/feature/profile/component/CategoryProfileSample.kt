package com.ahj.onlineshop.feature.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.feature.profile.domain.model.CategoryProfile


@Composable
fun CategorySample(categoryProfile: CategoryProfile , onClick:()-> Unit) {


    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {onClick()},
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                BackgroundCircleColor,
                                Color.White
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(categoryProfile.image),
                    null,
                    modifier = Modifier
                        .padding(25.dp)
                        .size(70.dp)

                )

            }

            Text(
                categoryProfile.name,
                style = MaterialTheme.typography.titleSmall
            )
            SpacerHeight(10)
        }
    }
}
