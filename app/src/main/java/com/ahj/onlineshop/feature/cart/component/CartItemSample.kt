package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.core.common.ui.component.SpacerWith
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.formatPriceToPersian
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.cart.domain.model.CartModel


@Composable
fun CartItemSample(cartModel: CartModel,modifier: Modifier, increase: () -> Unit, decrease: () -> Unit) {

    Card(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 6.dp)
                    .background(color = BackgroundColor, shape = CircleShape),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton({ increase() }) {
                    Icon(
                        Icons.Default.Add, null,
                        tint = Color.Green
                    )
                }

                Text(
                    "${cartModel.quantity}".toPersianDigit(),
                    style = MaterialTheme.typography.titleSmall
                )

                IconButton({ decrease() }) {
                    Icon(
                        if (cartModel.quantity > 1)
                            Icons.Default.Remove
                        else Icons.Default.Delete,
                        null,
                        tint = ButtonColor_Tow
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = BackgroundCardColor)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = cartModel.image,
                        contentDescription = null,
                    )
                }
            }
            SpacerWith(10)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    cartModel.title,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        cartModel.price.formatPriceToPersian(),
                        textDecoration = TextDecoration.LineThrough
                    )

                    SpacerWith(10)
                    Text(
                        cartModel.finalPrice.formatPriceToPersian(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

        }
        Spacer(Modifier.weight(1f))
    }
}
