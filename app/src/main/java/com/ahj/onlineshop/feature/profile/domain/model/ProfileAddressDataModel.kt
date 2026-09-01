package com.ahj.onlineshop.feature.profile.domain.model

import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel

data class ProfileAddressDataModel(
    val header : HeaderDataModel,
    val address: List<AddressModel>
)
