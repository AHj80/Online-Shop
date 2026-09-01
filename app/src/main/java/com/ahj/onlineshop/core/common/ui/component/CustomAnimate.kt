package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable


@Composable
fun CustomAnimate(
    state: Boolean,
    enter: Int = 600,
    exit: Int = 600,
    content: @Composable AnimatedVisibilityScope.(Transition<EnterExitState>) -> Unit
) {

    AnimatedVisibility(
        visible = state,
        enter = expandVertically(
            animationSpec = tween(enter)
        ) + fadeIn(tween(enter)),
        exit = shrinkVertically(
            animationSpec = tween(exit)
        ) + fadeOut(animationSpec = tween(exit)),
    ) {
        content(transition)

    }
}
