package com.ahj.onlineshop.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InsertBody(text: String , padding : Int = 30, color: Color = Color(0XFF938F8C)){

    Text(
        text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding.dp),
        color = color
    )

}

@Composable
fun InsertTitle(text: String , padding: Int = 30){

    Text(
        text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding.dp),
    )

}