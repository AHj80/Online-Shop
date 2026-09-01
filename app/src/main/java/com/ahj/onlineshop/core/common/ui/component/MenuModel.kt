package com.ahj.onlineshop.core.common.ui.component

import androidx.annotation.DrawableRes

internal data class MenuModel(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int
)
