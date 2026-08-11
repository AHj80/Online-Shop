package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@Composable
fun CustomOutlinedButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onClick()
        },
        border = BorderStroke(width = 1.dp, color = ButtonColor_Tow),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)

    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleSmall,
            color = ButtonColor_Tow
        )
    }
}
