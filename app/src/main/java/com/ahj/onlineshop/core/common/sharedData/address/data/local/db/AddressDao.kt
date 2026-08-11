package com.ahj.onlineshop.core.common.sharedData.address.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface AddressDao {

    @Insert
    suspend fun insertAddress(address: AddressEntity): Long

    @Query("SELECT * FROM AddressEntity")
    fun getAllAddress(): Flow<List<AddressEntity>>

    @Query("SELECT * FROM AddressEntity WHERE id = :id LIMIT 1")
    fun getAddressById(id: Int?): Flow<AddressEntity?>

    @Update
    suspend fun updateAddress(address: AddressEntity)

    @Delete
    suspend fun deleteAddress(address: AddressEntity)

}