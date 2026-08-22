package com.ahj.onlineshop.feature.cart.domain.model

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel

data class CartAddressModel(
    val cartCalculation : CartCalculation,
    val address: AddressModel
)
