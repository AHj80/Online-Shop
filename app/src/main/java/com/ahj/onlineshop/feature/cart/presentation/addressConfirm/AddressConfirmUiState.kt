package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.feature.cart.domain.model.CartCalculation


data class AddressConfirmUiState(
    val status: AddressConfirmStatus = AddressConfirmStatus.EMPTY,
    val message: String? = null,
    val address: AddressModel? = null,
    val data: CartCalculation? = null,
    val defaultAddress : Int? = null
)
