package com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserExperienceEntity(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val image: String,
    val comment: String,
    val rate : Int
)
