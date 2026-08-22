package com.ahj.onlineshop.core.sharedData.favorite.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val categoryType: String,
    val image: String,
    val title: String
)