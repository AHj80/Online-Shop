package com.ahj.onlineshop.core.sharedData.address.domain.model

data class AddressModel(
    val id: Int = 0,
    val receiver: String,
    val postalCode: String,
    val address: String,
    val phone: String
) {
    companion object {
        fun defaultValue(): AddressModel =
            AddressModel(
                0,
                "",
                "",
                "",
                "",
            )
    }
}
