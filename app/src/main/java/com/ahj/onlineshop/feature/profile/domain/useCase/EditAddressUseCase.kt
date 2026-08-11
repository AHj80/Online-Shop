package com.ahj.onlineshop.feature.profile.domain.useCase

import com.ahj.onlineshop.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject


class EditAddressUseCase @Inject constructor(
    private val repository: ProfileRepository
) {

    /*suspend operator fun invoke(id: String, addressId: String, addressModel: AddressModel): Result<AddressModel> =
        repository.editAddress(id, addressId, addressModel).fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(it)
            }
        )*/
}