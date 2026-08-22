package com.ahj.onlineshop.feature.profile.presentation.userAddress

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel


data class ProfileAddressUiState(
    val status: ProfileAddressStatus = ProfileAddressStatus.EMPTY,
    val message: String? = null,
    val data: List<AddressModel> = emptyList(),
    val modal: Boolean = false,
    val stateReceiver: String = "",
    val stateAddress: String = "",
    val statePhone: String = "",
    val statePostalCode: String = "",
    val address: AddressModel = AddressModel.defaultValue(),
    val addressDefault : Int = 0,
    val currentId : Int = 0
)