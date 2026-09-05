package com.ahj.onlineshop.core.sharedData.product.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val image: String,
    val quantity: Int,
    val price: Long,
    val finalPrice: Long,
    val discount: Int,
    val categoryType: String
)