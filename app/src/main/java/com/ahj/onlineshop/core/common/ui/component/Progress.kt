package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.R
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty


@Composable
fun Progress() {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.progress
        )
    )
    val dynamicLottieColor = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = ButtonColor_Tow.toArgb(),
            keyPath = arrayOf("**")
        )
    )
    LottieAnimation(
        composition = composition,
        iterations = Int.MAX_VALUE,
        modifier = Modifier.size(25.dp),
        contentScale = ContentScale.Crop,
        dynamicProperties = dynamicLottieColor
    )
}