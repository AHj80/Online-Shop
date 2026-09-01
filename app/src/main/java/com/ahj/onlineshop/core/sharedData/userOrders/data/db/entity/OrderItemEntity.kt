package com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    foreignKeys =[
        ForeignKey(
            entity = UserOrderEntity::class,
            parentColumns = ["id"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["orderId"])
    ]
)
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderId : Long,
    val productId: String,
    val image: String,
    val title: String,
    val quantity: Int,
    val categoryType: String
)
