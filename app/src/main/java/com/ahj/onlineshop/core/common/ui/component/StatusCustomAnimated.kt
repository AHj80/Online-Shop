package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> StatusCustomAnimated(
    state: T,
    modifier: Modifier = Modifier,
    delay: Int = 200,
    content: @Composable (T) -> Unit
) {
    AnimatedContent(
        targetState = state,
        modifier = modifier,
        transitionSpec = {
            expandVertically(tween(400)) +
                    fadeIn(animationSpec = tween(400, delayMillis = delay)) togetherWith (
                    fadeOut(animationSpec = tween(200))
                    )
        },
        label = "StatusAnimation"
    ) {
        content(it)
    }


}