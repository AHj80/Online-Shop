package com.ahj.onlineshop.core.sharedData.product.data.mapper

import com.ahj.onlineshop.core.sharedData.product.data.local.db.ProductEntity
import com.ahj.onlineshop.core.sharedData.product.domain.model.AddCartModel
import com.ahj.onlineshop.feature.cart.domain.model.CartModel


fun ProductEntity.toAddCartModel(): AddCartModel =
    AddCartModel(
        id = this.id,
        title = this.title,
        image = this.image,
        quantity = this.quantity,
        price = this.price,
        finalPrice = this.finalPrice,
        discount = this.discount
    )




fun ProductEntity.toDomain(): CartModel =
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