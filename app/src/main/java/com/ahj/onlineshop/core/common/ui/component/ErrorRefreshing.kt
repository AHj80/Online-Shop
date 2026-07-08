package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow

@Composable
fun ErrorRefreshing(
    message: String? ,
    onClick:() -> Unit = {},
){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton({
            onClick()
        }) {
            Icon(
                Icons.Default.Refresh,
                null,
                tint = ButtonColor_Tow
            )
        }

        SpacerHeight(20)

        message?.let {
            Text(it, style = MaterialTheme.typography.titleSmall)
        }
    }

}