package com.ahj.onlineshop.core.sharedData.favorite.data.repository

import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.favorite.data.local.db.FavoriteDao
import com.ahj.onlineshop.core.sharedData.favorite.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.favorite.data.mapper.toEntity
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import com.ahj.onlineshop.core.sharedData.favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val runCatching: RunCatching
) : FavoriteRepository {

    override suspend fun addFavorite(favorite: FavoriteModel): Result<Boolean> =
        runCatching.safeData {
            val result = favoriteDao.addFavorite(favorite.toEntity())
            result > 0
        }


    override fun getAllDataFavorite(): Flow<Result<List<FavoriteModel>>> =
        favoriteDao.getAllFavoriteData()
            .map { listData ->
                runCatching.safeData {
                    listData.map { data ->
                        data.toDomain()
                    }
                }
            }

    override suspend fun deleteFavorite(favorite: FavoriteModel): Result<Boolean> =
        runCatching.safeData {
            val result = favoriteDao.deleteFavorite(favorite.toEntity())
            result > 0
        }

    override fun isFavorite(id: Int): Flow<Result<Boolean>> =
        favoriteDao.isFavorite(id).map {
            runCatching.safeData {
                it
            }
        }


}