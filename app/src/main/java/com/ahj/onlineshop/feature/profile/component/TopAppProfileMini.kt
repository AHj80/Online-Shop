package com.ahj.onlineshop.feature.profile.component

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow

@Composable
fun TopAppProfileMini(image: Uri?, ) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),
        colors = CardDefaults.cardColors(containerColor = ButtonColor_Tow),
        elevation = CardDefaults.elevatedCardElevation(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    drawArc(
                        color = Color.White,
                        startAngle = -80f,
                        sweepAngle = 250f,
                        useCenter = false,
                        style = Stroke(width = 10f),
                    )
                }

                if (image == null)
                    Icon(
                        Icons.Default.Person,
                        null,
                        tint = Color.White
                    )
                else
                    AsyncImage(
                        model = image,
                        null,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
            }
            SpacerWith(20)
            Column {
                /*Text(
                    profileAddressModel.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                SpacerHeight(20)
                Text(
                    profileAddressModel.phone,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )*/
            }
        }
    }
}