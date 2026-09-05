package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.common.utils.toPersianDigit

@Composable
fun AddressItem(address: AddressModel, modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TextCart("نام گیرنده", address.receiver.toPersianDigit(), currencyUnit = false)
            TextCart("آدرس", address.address.toPersianDigit(),currencyUnit = false)
            TextCart("کد پستی", address.postalCode.toPersianDigit(),currencyUnit = false)
            TextCart("شماره همراه", address.phone.toPersianDigit(),currencyUnit = false)
        }
    }

}