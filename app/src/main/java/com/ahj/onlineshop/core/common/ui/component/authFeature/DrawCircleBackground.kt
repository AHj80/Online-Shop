package com.ahj.onlineshop.core.common.ui.component.authFeature

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor

@Composable
fun DrawCircleBackground(setUi:@Composable BoxScope.()-> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = BackgroundCircleColor,
                center = Offset(
                    x = 1000f ,
                    y = 200f,
                ),
                radius = 500f
            )
            drawCircle(
                color = BackgroundCircleColor,
                center = Offset(
                    x = 0f ,
                    y = 900f,
                ),
                radius = 200f
            )
            drawCircle(
                color = BackgroundCircleColor,
                center = Offset(
                    x = 100f ,
                    y = 2200f,
                ),
                radius = 400f
            )
        }
        setUi()
    }
}

