package com.ahj.onlineshop.core.sharedData.userProfile.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserInformationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInformation(profileData: UserInformationEntity)

    @Query("SELECT * FROM UserInformationEntity LIMIT 1")
    fun getUserInformation(): Flow<UserInformationEntity?>

    @Query("SELECT * FROM UserInformationEntity LIMIT 1")
    suspend fun getUserInformationFirst(): UserInformationModel?

    @Update
    suspend fun saveEditedData(editData: UserInformationEntity)
}