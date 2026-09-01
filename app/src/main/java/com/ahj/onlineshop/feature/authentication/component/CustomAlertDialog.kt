package com.ahj.onlineshop.feature.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.component.InsertButtonSecondary
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(text: String, onDismiss: () -> Unit) {

    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton({ onDismiss() }) {
                        Icon(
                            painter = painterResource(R.drawable.common_ui_component_closealert),
                            null,
                            tint = ButtonColor_Tow,
                        )
                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        textAlign = TextAlign.Center
                    )

                }

                InsertButtonSecondary(
                    modifier = Modifier.padding(
                        vertical = 20.dp,
                        horizontal = 70.dp
                    ), text = "متوجه شدم", fontSize = 12
                ) { onDismiss() }

            }
        }
    }

}