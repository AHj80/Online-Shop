package com.ahj.onlineshop.core.common.ui.component.authFeature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(text: String, onDismiss: () -> Unit) {

    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
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
                Text(text, style = MaterialTheme.typography.bodyMedium, fontSize = 12.sp)

            }
        }
    }

}