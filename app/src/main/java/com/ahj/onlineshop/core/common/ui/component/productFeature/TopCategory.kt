package com.ahj.onlineshop.core.common.ui.component.productFeature

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun TopCategory(
    data: List<CategoryData>,
    categoryType: String,
    onClick: (CategoryData) -> Unit
) {

    val currentIndex = data.indexOfFirst { it.categoryType == categoryType }.coerceAtLeast(0)



    SecondaryScrollableTabRow(
        selectedTabIndex = currentIndex,
        containerColor = Color.Transparent,
        indicator = {},
        divider = {},
        edgePadding = 10.dp,
        minTabWidth = 60.dp,
    ) {


            data.forEachIndexed { index, tab ->

                val selected = index == currentIndex
                Tab(
                    selected = selected,
                    onClick = {
                        onClick(tab)
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .size(if (selected) 70.dp else 50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = if (selected) BorderStroke(2.dp, Color.Red) else null
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(tab.image),
                                null,
                                modifier = Modifier
                                    .size(if (selected) 50.dp else 30.dp)
                            )
                        }
                    }
                }
            }
        }




}