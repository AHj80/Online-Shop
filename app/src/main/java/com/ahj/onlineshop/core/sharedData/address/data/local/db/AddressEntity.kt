package com.ahj.onlineshop.core.sharedData.address.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val receiver: String,
    val postalCode: String,
    val address: String,
    val phone: String
)
