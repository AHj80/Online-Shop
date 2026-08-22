package com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserExperienceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExperience(experience: UserExperienceEntity): Long

    @Query("SELECT * FROM UserExperienceEntity")
    fun getExperience(): Flow<List<UserExperienceEntity>>
}