package com.ahj.onlineshop.core.sharedData.favorite.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity): Long

    @Query("SELECT * FROM FavoriteEntity")
    fun getAllFavoriteData(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity): Int

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteEntity WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>
}