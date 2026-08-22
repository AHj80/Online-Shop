package com.ahj.onlineshop.core.sharedData.address.domain.useCase

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.sharedData.address.domain.repository.AddressRepository
import javax.inject.Inject

class EditAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(address: AddressModel): Result<Boolean> =  repository.updateAddress(address)
}