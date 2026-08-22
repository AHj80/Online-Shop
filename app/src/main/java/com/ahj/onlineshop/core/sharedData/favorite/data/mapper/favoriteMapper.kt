package com.ahj.onlineshop.core.sharedData.favorite.data.mapper

import com.ahj.onlineshop.core.sharedData.favorite.data.local.db.FavoriteEntity
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel


fun FavoriteEntity.toDomain(): FavoriteModel =
    FavoriteModel(
        this.id,
        this.categoryType,
        this.image,
        this.title
    )

fun FavoriteModel.toEntity(): FavoriteEntity  =
    FavoriteEntity(
        this.id,
        this.categoryType,
        this.image,
        this.title
    )