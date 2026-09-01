package com.ahj.onlineshop.feature.product.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ahj.onlineshop.core.common.ui.component.CustomAnimate
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.feature.product.domain.model.ProductModel


@Composable
fun SearchProduct(
    data: List<ProductModel>,
    stateSearch: String,
    onClick:(ProductModel)-> Unit
) {

    val resultSearch =
        data.filter { it.title.contains(stateSearch, ignoreCase = true) }


    CustomAnimate(stateSearch.isNotBlank()){
        Card(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(7.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(resultSearch.size, key = { resultSearch[it].id}) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(
                                fadeInSpec = tween(100),
                                fadeOutSpec = tween(100),
                                placementSpec = spring(stiffness = Spring.StiffnessLow)
                            )
                            .clickable{
                                onClick(resultSearch[it])
                            }
                    ) {

                        Text(
                            resultSearch[it].title,
                            style = MaterialTheme.typography.titleSmall
                        )
                        SpacerHeight(10)
                        AsyncImage(
                            model = resultSearch[it].image[0],
                            contentDescription = null,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    if (it != data.lastIndex)
                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 50.dp, vertical = 10.dp)
                        )

                }
                item {
                    if (resultSearch.isEmpty())
                        Text(
                            "کالایی یافت نشد...!",
                            style = MaterialTheme.typography.titleSmall
                        )
                }

            }
        }
    }

}