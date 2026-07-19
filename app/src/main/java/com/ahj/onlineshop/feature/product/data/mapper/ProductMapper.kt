package com.ahj.onlineshop.feature.product.data.mapper

import com.ahj.onlineshop.feature.product.data.remote.dto.CommentDto
import com.ahj.onlineshop.feature.product.data.remote.dto.ProductDto
import com.ahj.onlineshop.feature.product.domain.model.CommentModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

fun ProductDto.toModel(): ProductModel {
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
        comments = this.comments.map { it.toModel() }
    )
}

fun CommentDto.toModel(): CommentModel {
    return CommentModel(
        id = this.id,
        name = this.name,
        date = this.date,
        rate = this.rate,
        text = this.text
    )
}
