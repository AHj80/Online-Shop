package com.ahj.onlineshop.feature.cart.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.toPersianDigit


@Composable
fun EditAddressItem(
    address: AddressModel,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    selectedBorder: Boolean = false,
    onClick: () -> Unit,
    editClick: () -> Unit,
    deleteClick: () -> Unit
) {
    val animateColor by animateColorAsState(
        targetValue = if (selectedBorder) ButtonColor_Tow else Color.Transparent,
        animationSpec = tween(300)
    )

    Card(
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .border(
                shape = RoundedCornerShape(15.dp),
                width = 1.dp,
                color = animateColor
            ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TextCart("نام گیرنده", address.receiver.toPersianDigit(), currencyUnit = false)
            TextCart("آدرس", address.address.toPersianDigit(), currencyUnit = false)
            TextCart("کد پستی", address.postalCode.toPersianDigit(), currencyUnit = false)
            TextCart("شماره همراه", address.phone.toPersianDigit(), currencyUnit = false)

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RadioButton(
                    selected = selected,
                    onClick = { onClick() },
                    colors = RadioButtonDefaults.colors(selectedColor = ButtonColor_Tow),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "آدرس پیشفرض",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp
                )

                Button(
                    shape = RoundedCornerShape(15.dp),
                    onClick = { editClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroundColor
                    )
                ) {
                    Text(
                        "ویرایش",
                        style = MaterialTheme.typography.titleSmall,
                        color = ButtonColor_Tow,
                        fontSize = 10.sp
                    )
                }

                Button(
                    { deleteClick() },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroundCardColor
                    ),
                    modifier = Modifier
                ) {
                    Text(
                        "حذف",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }

}