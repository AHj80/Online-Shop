package com.ahj.onlineshop.feature.profile.domain.model

import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel


data class FavoriteDataModel (
    val headerData : HeaderDataModel,
    val favoriteData : List<FavoriteModel>
)