package com.ahj.onlineshop.feature.product.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class CommentDto(
    val id: String,
    val name: String,
    val date: String,
    val rate: String,
    val text: String,
)
