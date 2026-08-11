package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.SpacerWith


@Composable
fun TextCart(title: String, text: String, color: Color = Color.Black , currencyUnit: Boolean = true) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        if (!currencyUnit)
        SpacerWith(20)

            Text(
                if (currencyUnit) "$text  تومان " else text,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 16.sp,
                color = color,

            )

    }
}