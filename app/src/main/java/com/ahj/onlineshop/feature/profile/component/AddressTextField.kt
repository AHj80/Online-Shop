package com.ahj.onlineshop.feature.profile.component

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow


@Composable
fun AddressTextField(
    stateText: String,
    readOnly: Boolean,
    prefix: String,
    onValueChange: (String) -> Unit
) {


    TextField(
        value = stateText,
        onValueChange = { onValueChange(it) },
        prefix = {
            Text(
                prefix,
                style = MaterialTheme.typography.titleSmall
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            selectionColors = TextSelectionColors(ButtonColor_Tow, ButtonColor_Tow),
            cursorColor = ButtonColor_Tow,
            focusedTextColor = if (!readOnly) Color.Black else Color.Gray,
            unfocusedTextColor = if (!readOnly) Color.Black else Color.Gray
        ),
        readOnly = readOnly
    )
}