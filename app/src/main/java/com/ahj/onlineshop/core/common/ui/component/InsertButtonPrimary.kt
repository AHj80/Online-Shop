package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_One
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow_Disable


@Composable
fun InsertButtonPrimary(
    text : String,
    enabled : Boolean = true,
    padding : Int = 30,
    click:()-> Unit
){

    Button(

        click,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = padding.dp)
            .background(
                Brush.horizontalGradient(
                    if (enabled)
                        listOf(
                            ButtonColor_Tow,
                            ButtonColor_One
                        ) else listOf(
                        ButtonColor_Tow_Disable,
                        ButtonColor_Tow_Disable

                    )
                ),
                shape = RoundedCornerShape(10.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent , disabledContainerColor = Color.Transparent
        ),
        enabled = enabled

    ) {
        Text(
            text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}