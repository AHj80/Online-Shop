package com.ahj.onlineshop.feature.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ahj.onlineshop.feature.product.domain.model.ProductModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBestSell(
    data: List<ProductModel>,
    state: (Boolean) -> Unit,
    clickable: (ProductModel) -> Unit,
    addOnClick: (ProductModel) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { state(false) },
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 100.dp),
        containerColor = Color.White,
        tonalElevation = 10.dp
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(data.size) {
                ProductItemSample(
                    productModel = data[it], onClick = { clickable(data[it]) },
                ) {
                    addOnClick(data[it])
                }
            }

            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Text(
                    "اتمام لیست",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }


    }

}
