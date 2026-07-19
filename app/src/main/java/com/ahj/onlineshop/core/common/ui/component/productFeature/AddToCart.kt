package com.ahj.onlineshop.core.common.ui.component.productFeature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.InsertButtonSecondary
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.core.common.utils.toPersianDigit


@Composable
fun AddToCart(
    price: String,
    finalPrice: String,
    discount: Int,
    quantity: Int,
    increase: () -> Unit,
    decrease: () -> Unit

) {

    Row(
        modifier = Modifier
            .clip(CutCornerShape(30.dp))
            .fillMaxWidth()
            .navigationBarsPadding()
            .background(BackgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (discount!=0)
                Text(
                    price.toPersianDigit(),
                    style = MaterialTheme.typography.titleSmall,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    "${finalPrice.toPersianDigit()} تومان ",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    {
                        increase()
                    },
                    modifier = Modifier.width(45.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        "+",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }

                Text(
                    quantity.toPersianDigit(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )

                Button(
                    {
                        decrease()
                    },
                    modifier = Modifier.size(45.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        "-",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }

                InsertButtonSecondary(
                    modifier = Modifier.width(200.dp),
                    text = "افزودن به سبد خرید"
                ) { }
            }
        }
    }

}