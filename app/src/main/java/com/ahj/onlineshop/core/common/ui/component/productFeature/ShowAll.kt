package com.ahj.onlineshop.core.common.ui.component.productFeature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow

@Composable
fun ShowAll(title: String , clickable: ()-> Unit  = {}){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.weight(1f))
        Text(
            "مشاهده همه ",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp
        )


        Icon(
            Icons.Default.ArrowUpward,
            null,
            tint = ButtonColor_Tow,
            modifier = Modifier.clickable {clickable()}
        )

    }
}