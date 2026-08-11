package com.ahj.onlineshop.feature.profile.presentation.address


data class AddressUiState(
    val status: AddressStatus = AddressStatus.EMPTY,
    val message: String? = null,

    val avatar: String? = null,
    val userId: String = "",
    val receiverText : String = "",
    val addressText: String = "",
    val postalCodeText: String = "",
    val phoneText: String = "",
    val readOnly: Boolean = true,
    val currentAddressId: String = "",
    val selectedDefault: String? = null
)
