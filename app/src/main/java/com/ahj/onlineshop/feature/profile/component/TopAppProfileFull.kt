package com.ahj.onlineshop.feature.profile.component

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel


@Composable
fun TopAppProfileFull(
    profileModel: UserInformationModel,
    image: Uri?,
    editOnClick: () -> Unit,
    cameraOnClick: () -> Unit
) {


    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp),
        colors = CardDefaults.cardColors(containerColor = ButtonColor_Tow),
        elevation = CardDefaults.elevatedCardElevation(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerHeight(20)

            Text(
                "پروفایل کاربری",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
            )
            SpacerHeight(20)

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton({
                        cameraOnClick()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.feature_profile_camera),
                            null,
                            tint = BackgroundCardColor
                        )
                    }

                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(100.dp)
                        ) {
                            drawArc(
                                color = Color.White,
                                startAngle = -80f,
                                sweepAngle = 250f,
                                useCenter = false,
                                style = Stroke(width = 10f),
                            )
                        }

                        if (image == null){

                            Icon(
                                Icons.Default.Person,
                                null,
                                tint = Color.White,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        else{

                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(image)
                                    .crossfade(true)
                                    .build(),
                                null,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,

                                )
                        }

                    }

                    IconButton({
                        editOnClick()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.feature_profile_edit),
                            null,
                            tint = BackgroundCardColor
                        )
                    }
                }
            }
            SpacerHeight(20)

            profileModel.name?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
            SpacerHeight(10)
            profileModel.phone?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            SpacerHeight(10)
        }
    }
}