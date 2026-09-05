package com.ahj.onlineshop.feature.profile.presentation.userAddress

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class ProfileAddressUiState(
    val status: ProfileAddressStatus = ProfileAddressStatus.EMPTY,
    val messageStatus: String? = null,
    val message: String? = null,
    val allAddress: List<AddressModel> = emptyList(),
    val header: HeaderDataModel? = null,
    val modal: Boolean = false,
    val stateReceiver: String = "",
    val stateAddress: String = "",
    val statePhone: String = "",
    val statePostalCode: String = "",
    val address: AddressModel? = null,
    val addressDefault : Int = 0,
    val currentId : Int = 0
)