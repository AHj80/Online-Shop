package com.ahj.onlineshop.core.common.sharedData.address.domain.useCase

import com.ahj.onlineshop.core.common.sharedData.address.domain.repository.AddressRepository
import javax.inject.Inject

class GetAddressByIdUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    operator fun invoke(id: Int?) = repository.getAddressById(id)
}