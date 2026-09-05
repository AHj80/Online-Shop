package com.ahj.onlineshop.core.sharedData.address.data.repository

import com.ahj.onlineshop.core.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.sharedData.address.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.address.data.mapper.toEntity
import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.sharedData.address.domain.repository.AddressRepository
import com.ahj.onlineshop.core.common.utils.RunCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao,
    private val runCatching: RunCatching
) : AddressRepository {
    override suspend fun insertAddress(address: AddressModel): Result<Long> =
        runCatching.safeData {
            addressDao.insertAddress(address.toEntity())
        }


    override fun getAllAddress(): Flow<Result<List<AddressModel>>> =
        addressDao.getAllAddress()
            .map { list ->
                runCatching.safeData {
                    list.map {
                        it.toDomain()
                    }
                }
            }


    override fun getAddressById(id: Int?): Flow<Result<AddressModel>> =
        addressDao.getAddressById(id).map { address ->
            runCatching.safeData {
                address?.toDomain()?: AddressModel.defaultValue()
            }
        }

    override suspend fun updateAddress(address: AddressModel): Result<Boolean> =

        runCatching.safeData {
            addressDao.updateAddress(address.toEntity())
            true
        }


    override suspend fun deleteAddress(address: AddressModel): Result<Boolean> =
        runCatching.safeData {
            addressDao.deleteAddress(address.toEntity())
            true
        }



}