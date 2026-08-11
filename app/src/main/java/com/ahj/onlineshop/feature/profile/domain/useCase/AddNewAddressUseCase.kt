package com.ahj.onlineshop.feature.profile.domain.useCase

import com.ahj.onlineshop.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class AddNewAddressUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
/*
    suspend operator fun invoke(id: String, addressModel: AddressModel): Result<AddressModel> =
        profileRepository.newAddress(id, addressModel).fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(it)
            }
        )*/

}