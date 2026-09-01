package com.ahj.onlineshop.feature.cart.data.mapper

import com.ahj.onlineshop.core.sharedData.product.local.db.ProductEntity
import com.ahj.onlineshop.feature.cart.domain.model.CartModel


fun CartModel.toCartEntity(quantity: Int = 1): ProductEntity =
    ProductEntity(
        id = this.id,
        title = this.title,
        image = this.image,
        quantity = quantity,
        price = this.price,
        finalPrice = this.finalPrice,
        discount = this.discount,
        categoryType = this.categoryType
    )

fun ProductEntity.toModel(): CartModel =
    CartModel(
        id = this.id,
        title = this.title,
        image = this.image,
        quantity = quantity,
        price = this.price,
        finalPrice = this.finalPrice,
        discount = this.discount,
        categoryType = this.categoryType
    )

