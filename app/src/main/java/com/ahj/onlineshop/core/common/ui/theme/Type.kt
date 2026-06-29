package com.ahj.onlineshop.core.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.R

val iranSans = FontFamily(
    Font(
        R.font.iran_sans,
    )

)
val Typography = Typography(
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = iranSans,
        fontWeight = FontWeight.Bold,
        textDirection = TextDirection.Rtl
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = iranSans,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Justify
    ),

    )