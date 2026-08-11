package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable


@Composable
fun CustomAnimate(
    state: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = state,
        enter = expandVertically(
            animationSpec = tween(600)
        ) + fadeIn(tween(600)),
        exit = shrinkVertically(
            animationSpec = tween(600)
        ) + fadeOut(animationSpec = tween(600)),
    ) {
        content()
    }
}
