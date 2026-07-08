package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor

@Composable
fun InsertDialog(
    onDismiss: () -> Unit,
    text: String
) {

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {

        Card(
            modifier = Modifier
                .width(200.dp)
                .height(70.dp),
            colors = CardDefaults.cardColors(BackgroundColor)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text, style = MaterialTheme.typography.bodyMedium , fontSize = 12.sp)
                SpacerWith(30)
                Progress()
            }
        }
    }
}