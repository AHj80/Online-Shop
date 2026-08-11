package com.ahj.onlineshop.feature.cart.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.InsertButtonPrimary
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.feature.authentication.component.InsertBody
import com.ahj.onlineshop.feature.authentication.component.InsertTextFieldAuth
import com.ahj.onlineshop.feature.authentication.component.InsertTitle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomModal(
    receiver: String,
    receiverChange: (String) -> Unit,
    address: String,
    addressChange: (String) -> Unit,
    phone: String,
    phoneChange: (String) -> Unit,
    postalCode: String,
    postalCodeChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onClick: () -> Unit,
    closeButton:()-> Unit,
    enabled: Boolean = true
) {

    ModalBottomSheet(
        modifier = Modifier
            .imePadding(),
        onDismissRequest = { onDismiss() },
        containerColor = BackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    "لطفا اطلاعات تمامی فیلد ها را وارد نمایید",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )

                IconButton({
                    closeButton()
                }) {
                    Icon(Icons.Default.Close, null, tint = ButtonColor_Tow)
                }
            }

            InsertTextFieldAuth(
                receiver,
                { receiverChange(it) },
                "نام گیرنده"
            )
            SpacerHeight(10)

            InsertTextFieldAuth(
                address,
                { addressChange(it) },
                "آدرس گیرنده",
                singleLine = false
            )
            SpacerHeight(10)

            InsertTextFieldAuth(
                phone,
                { phoneChange(it) },
                "شماره تماس",
                keyboardType = KeyboardType.Phone
            )
            SpacerHeight(10)

            InsertTextFieldAuth(
                postalCode,
                { postalCodeChange(it) },
                "کد پستی",
                keyboardType = KeyboardType.Number
            )


            InsertButtonPrimary("ذخیره اطلاعات", enabled = enabled) { onClick() }


        }
    }
}