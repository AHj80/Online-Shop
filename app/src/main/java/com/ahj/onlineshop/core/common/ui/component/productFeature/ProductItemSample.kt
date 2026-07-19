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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.formatPriceToPersian
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

@Composable
fun ProductItemSample(productModel: ProductModel, onClick: () -> Unit = {}) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = Modifier
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
                    model = productModel.image[0],
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.loading_coil)
                )
            }

            if (productModel.discount != 0)
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
                            .padding(horizontal = 10.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Text(
                            productModel.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 12.sp
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

                                if (productModel.discount !=0)
                                Text(
                                    productModel.price.formatPriceToPersian().toPersianDigit(),
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 8.sp,
                                    color = Color.DarkGray,
                                    textDecoration = TextDecoration.LineThrough
                                )


                                SpacerWith(10)
                                Text(
                                    "${
                                        productModel.finalPrice.formatPriceToPersian()
                                            .toPersianDigit()
                                    } تومان",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 9.sp
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
                        .clickable {},
                    contentAlignment = Alignment.Center

                ) {
                    Text("+", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    }
}