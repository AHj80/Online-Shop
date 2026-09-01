package com.ahj.onlineshop.core.sharedData.userOrders.data.mapper

import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.OrderItemEntity
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.OrderWithItem
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.UserOrderEntity
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.OrderItemModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel




fun UserOrderModel.toEntity(): UserOrderEntity =
    UserOrderEntity(
        date = this.date,
        orderResult = this.orderResult,
        orderCode = this.orderCode,
        orderPrice = this.orderPrice
    )

fun OrderItemEntity.toDomain(): OrderItemModel =
    OrderItemModel(
        this.id,
        this.orderId,
        this.productId,
        this.image,
        this.title,
        this.quantity,
        this.categoryType
    )

fun OrderItemModel.toEntity(): OrderItemEntity =
    OrderItemEntity(
        orderId = this.orderId,
        productId = this.productId,
        image = this.image,
        title = this.title,
        quantity = this.quantity,
        categoryType = this.categoryType
    )

fun OrderWithItem.toDomain(): UserOrderModel =
    UserOrderModel(
        id = orders.id,
        date = orders.date,
        orderResult = orders.orderResult,
        orderCode = orders.orderCode,
        orderPrice = orders.orderPrice,
        item = items.map { it.toDomain() }
    )