package com.ahj.onlineshop.feature.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow

@Composable
fun InsertTextFieldAuth(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: Boolean = true,
    singleLine : Boolean = true,
    trailingIcon: @Composable () -> Unit = {},
    leadingIcon:@Composable ()-> Unit = {}
) {

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent,
            errorContainerColor = Color.White,
            cursorColor = ButtonColor_Tow,
            selectionColors = TextSelectionColors(ButtonColor_Tow, ButtonColor_Tow)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholder = { InsertBody(placeholder, 0) },
        singleLine = singleLine,
        supportingText = {
            supportingText?.let {
                InsertBody(it, padding = 0)
            }
        },
        isError = isError,
        visualTransformation = if (visualTransformation) {
            VisualTransformation.None
        } else PasswordVisualTransformation(),
        trailingIcon = { trailingIcon() },
        leadingIcon = { leadingIcon() },
        textStyle = MaterialTheme.typography.titleSmall
    )
}