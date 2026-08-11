package com.ahj.onlineshop.feature.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InsertCategoryGrid(
    data: List<CategoryData>,
    onClick: (CategoryData) -> Unit = {}
) {

    FlowRow(
        itemVerticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(
            2.dp,
            alignment = Alignment.CenterHorizontally
        ),
        maxItemsInEachRow = 3
    ) {
        data.forEach { category ->
            CategoriesSample(category, onClick = { onClick(category) })
        }
    }

}