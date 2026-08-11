package com.ahj.onlineshop.core.common.sharedData.address.data.mapper

import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressEntity
import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel


fun AddressEntity.toDomain(): AddressModel =
    AddressModel(
        id = this.id,
        receiver = this.receiver,
        postalCode = this.postalCode,
        address = this.address,
        phone = this.phone
    )

fun AddressModel.toEntity(): AddressEntity =
    AddressEntity(
        id = this.id,
        receiver = this.receiver,
        postalCode = this.postalCode,
        address = this.address,
        phone = this.phone

    )