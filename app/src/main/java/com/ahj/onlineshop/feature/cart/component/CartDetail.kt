package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.formatPriceToPersian
import com.ahj.onlineshop.core.common.utils.toPersianDigit


@Composable
fun CartDetail(
    price: Long,
    finalPrice: Long,
    discount: Long,
    address: Boolean = false,
    text: String,
    onClick : ()-> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SpacerHeight(10)
            TextCart("جمع قیمت", price.formatPriceToPersian())
            if (address) TextCart("هزینه ارسال", 100000L.formatPriceToPersian())
            TextCart("تخفیف", discount.formatPriceToPersian(), color = ButtonColor_Tow)
            SpacerHeight(20)
            TextCart("مبلغ نهایی", finalPrice.formatPriceToPersian())
        }
        SpacerHeight(10)
        InsertButtonPrimary(text) { onClick()}
    }

}