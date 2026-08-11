package com.ahj.onlineshop.core.common.sharedData.address.domain.useCase

import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.common.sharedData.address.domain.repository.AddressRepository
import javax.inject.Inject

class InsertAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(address: AddressModel): Result<Long> =
        repository.insertAddress(address)

}