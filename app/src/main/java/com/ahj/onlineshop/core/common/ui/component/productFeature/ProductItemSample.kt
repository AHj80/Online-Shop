package com.ahj.onlineshop.core.common.ui.component.productFeature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.ahj.onlineshop.core.common.ui.component.InsertDialog
import com.ahj.onlineshop.core.common.ui.component.Progress
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

@Composable
fun ProductItemSample(productModel: ProductModel){

    Card(
        colors = CardDefaults.cardColors(BackgroundCardColor),
        shape = RoundedCornerShape(23.dp),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        modifier = Modifier
            .padding(20.dp)
            .width(195.dp)
            .height(250.dp)

    ) {

        Box {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                SubcomposeAsyncImage(
                  model = productModel.image[0],
                    contentDescription = null,
                    loading = {Progress()},
                    modifier = Modifier.size(130.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                ButtonColor_One,
                                ButtonColor_Tow
                            )
                        ), shape = RoundedCornerShape(bottomEnd = 21.dp, topEnd = 21.dp)
                    )

            ) {

                Text(
                    "${productModel.discount.toPersianDigit()}%",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 12.sp
                )
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
                            .padding(horizontal = 15.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Text(
                            productModel.title,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp, start = 7.dp, end = 7.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,

                                ) {

                                Text(
                                    productModel.price.toString().toPersianDigit(),
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 9.sp,
                                    color = Color.DarkGray
                                )


                                SpacerWith(10)
                                Text(
                                    "${productModel.finalPrice.toString().toPersianDigit()}تومان",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 11.sp
                                )

                            }
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                HorizontalDivider(
                                    thickness = 1.5.dp,
                                    modifier = Modifier
                                        .width(45.dp)
                                        .rotate(-5f)
                                        .padding(bottom = 6.dp)
                                )
                            }
                        }
                    }
                }
            }


            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 35.dp, end = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    ButtonColor_One,
                                    ButtonColor_Tow
                                )
                            ),
                            shape = CircleShape
                        )
                        .size(30.dp)
                        .clickable{},
                    contentAlignment = Alignment.Center

                ) {
                    Text("+", color = Color.White , fontSize = 20.sp)
                }
            }
        }
    }
}