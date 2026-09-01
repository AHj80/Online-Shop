package com.ahj.onlineshop.feature.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel

@Composable
fun UserOrderItemSample(
    userOrderModel: UserOrderModel,
    navigateOnClick: (String, String) -> Unit

) {

    var showDetail by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (userOrderModel.orderResult)
                Color(0XFFDFF1CF)
            else Color(0XFFFFE2E2)
        )
    ) {

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    shape = CircleShape,
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = if (userOrderModel.orderResult)
                                    Color(0XFF597D3C)
                                else
                                    Color(0XFFEF472C)
                            ),
                            shape = CircleShape
                        )
                ) {

                    Text(
                        if (userOrderModel.orderResult) "پرداخت موفق" else "پرداخت ناموفق",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(10.dp),
                        color = if (userOrderModel.orderResult)
                            Color(0XFF597D3C)
                        else
                            Color(0XFFEF472C)
                    )

                }

                Text(
                    userOrderModel.orderPrice.toPersianDigit(),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    "کد سفارش : ${userOrderModel.orderCode}",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp
                )
                Text(
                    userOrderModel.date,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                TextButton({
                    showDetail = !showDetail
                }) {
                    Text(
                        "جزئیات سفارش",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                    )
                    Icon(
                        if (showDetail) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        null,
                        modifier = Modifier.size(20.dp)
                    )
                }

            }

            CustomAnimate(showDetail, 500, 500) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    userOrderModel.item.forEach { item ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable {
                                        navigateOnClick(item.productId, item.categoryType)
                                    },
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(R.drawable.loading_coil)
                            )

                            Text(
                                "${item.title} به تعداد ${item.quantity} عدد ".toPersianDigit(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 10.sp
                            )
                        }


                    }
                }
            }

        }

    }
}