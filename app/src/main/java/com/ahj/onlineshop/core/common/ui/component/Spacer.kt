package com.ahj.onlineshop.core.common.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SpacerWith(int: Int){
    Spacer(Modifier.width(int.dp))
}

@Composable
fun SpacerHeight(int: Int){
    Spacer(Modifier.height(int.dp))
}