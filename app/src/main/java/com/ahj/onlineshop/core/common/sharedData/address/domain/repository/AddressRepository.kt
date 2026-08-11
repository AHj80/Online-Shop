package com.ahj.onlineshop.core.common.sharedData.address.domain.repository

import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel
import kotlinx.coroutines.flow.Flow


interface AddressRepository {

    suspend fun insertAddress(address: AddressModel): Result<Long>

    fun getAllAddress(): Flow<Result<List<AddressModel>>>

    fun getAddressById(id: Int?): Flow<Result<AddressModel>>

    suspend fun updateAddress(address: AddressModel):Result<Boolean>

    suspend fun deleteAddress(address: AddressModel): Result<Boolean>
}