package com.ahj.onlineshop.feature.product.data.mapper

import com.ahj.onlineshop.core.sharedData.product.local.db.ProductEntity
import com.ahj.onlineshop.feature.product.data.remote.dto.CommentDto
import com.ahj.onlineshop.feature.product.data.remote.dto.ProductDto
import com.ahj.onlineshop.feature.product.domain.model.CommentModel
import com.ahj.onlineshop.feature.product.domain.model.AddCartModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

fun ProductDto.toProductModel(): ProductModel {
    return ProductModel(
        id = this.id,
        title = this.title,
        desc = this.desc,
        image = this.image,
        category = this.category,
        price = this.price,
        discount = this.discount,
        rating = this.rating,
        sales = this.sales,
        categoryType = this.categoryType,
        features = this.features,
        comments = this.comments.map { it.toCommentModel() }
    )
}

fun CommentDto.toCommentModel(): CommentModel {
    return CommentModel(
        id = this.id,
        name = this.name,
        date = this.date,
        rate = this.rate,
        text = this.text
    )
}


fun ProductModel.toEntity(quantity: Int = 1): ProductEntity =
    ProductEntity(
        id = this.id,
        title = this.title,
        image = this.image[0],
        quantity = quantity,
        price = this.price,
        finalPrice = this.finalPrice,
        discount = this.discount,
        this.categoryType
    )

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