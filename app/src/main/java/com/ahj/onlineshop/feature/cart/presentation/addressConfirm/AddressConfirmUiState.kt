package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.feature.cart.domain.model.CartCalculation
import java.util.UUID


data class AddressConfirmUiState(
    val status: AddressConfirmStatus = AddressConfirmStatus.EMPTY,
    val message: String? = null,
    val address: AddressModel? = null,
    val data: CartCalculation? = null,
    val defaultAddress: Int? = null,
    val orderCode: String = UUID.randomUUID().toString().take(5).uppercase(),
    val orderResult: Boolean = false,
    val resultPaymentDialog: Boolean = false,
    val paymentText: String? = null,
    val navigating: Boolean = false
)
