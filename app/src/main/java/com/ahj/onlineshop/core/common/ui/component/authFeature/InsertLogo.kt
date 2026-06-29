package com.ahj.onlineshop.core.common.ui.component.authFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.R


@Composable
fun InsertLogo(one: Float = 0.7f , tow : Float =0.7f , three : Float = 0.7f){



        Image(
            painterResource(R.drawable.logo_online_shop),
            null,
            modifier = Modifier.scale(one)
        )
        Spacer(Modifier.height(10.dp))
        Image(
            painterResource(R.drawable.logo_online_shop_text),
            null,
            modifier = Modifier.scale(tow)
        )
        Spacer(Modifier.height(10.dp))
        Image(
            painterResource(R.drawable.logo_online_shop_text_persian),
            null,
            modifier = Modifier.scale(three)
        )


}