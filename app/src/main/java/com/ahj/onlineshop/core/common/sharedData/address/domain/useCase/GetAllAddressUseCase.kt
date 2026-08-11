package com.ahj.onlineshop.core.common.sharedData.address.domain.useCase

import com.ahj.onlineshop.core.common.sharedData.address.domain.repository.AddressRepository
import javax.inject.Inject

class GetAllAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {

    operator fun invoke() = repository.getAllAddress()


}