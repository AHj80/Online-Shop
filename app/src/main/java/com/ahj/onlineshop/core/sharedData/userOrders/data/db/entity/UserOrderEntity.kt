package com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserOrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val orderResult: Boolean,
    val orderCode: String,
    val orderPrice: String
)