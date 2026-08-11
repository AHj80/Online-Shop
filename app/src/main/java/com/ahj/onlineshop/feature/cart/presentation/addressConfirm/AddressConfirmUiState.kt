package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.feature.cart.domain.model.CartCalculation


data class AddressConfirmUiState(
    val status: AddressConfirmStatus = AddressConfirmStatus.EMPTY,
    val message: String? = null,
    val address: AddressModel = AddressModel.defaultValue(),
    val data: CartCalculation = CartCalculation.defaultValue(),
    val defaultAddress : Int? = null
)
