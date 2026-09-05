package com.ahj.onlineshop.feature.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.InsertButtonSecondary
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.cart.component.CustomOutlinedButton


@Composable
fun AddToCart(
    price: String,
    finalPrice: String,
    discount: Int,
    statusButton: Boolean,
    addOnClick: () -> Unit,
    navigating: () -> Unit
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
                if (discount != 0)
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

            CustomAnimate(!statusButton, 80, 80) {
                CustomOutlinedButton(
                    "افزودن به سبد خرید"
                ) { addOnClick() }
            }

            CustomAnimate(statusButton, 80, 80) {
                InsertButtonSecondary(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = "رفتن به سبد خرید"
                ) { navigating() }

            }

        }
    }

}