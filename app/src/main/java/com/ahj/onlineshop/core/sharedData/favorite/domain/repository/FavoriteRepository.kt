package com.ahj.onlineshop.core.sharedData.favorite.domain.repository

import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun addFavorite(favorite: FavoriteModel): Result<Boolean>

    fun getAllDataFavorite(): Flow<Result<List<FavoriteModel>>>

    suspend fun deleteFavorite(favorite: FavoriteModel): Result<Boolean>

    fun isFavorite(id: Int): Flow<Result<Boolean>>
}