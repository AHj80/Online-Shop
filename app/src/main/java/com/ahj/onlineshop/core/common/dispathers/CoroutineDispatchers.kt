package com.ahj.onlineshop.core.common.dispathers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class CoroutineDispatchers(
    val main : CoroutineDispatcher = Dispatchers.Main,
    val io : CoroutineDispatcher = Dispatchers.IO,
    val default : CoroutineDispatcher = Dispatchers.Default
)
