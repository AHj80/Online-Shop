package com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithItem(
    @Embedded
    val orders: UserOrderEntity,
    @Relation(
        parentColumn = "id", // -> Primary Key
        entityColumn = "orderId" // -> ForeignKey
    )
    val items : List<OrderItemEntity>
)
