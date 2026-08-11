package com.ahj.onlineshop.feature.cart.domain.model

import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel

data class CartAddressModel(
    val cartCalculation : CartCalculation,
    val address: AddressModel
)
