package com.ahj.onlineshop.feature.product.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.product.domain.model.CommentModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

@Composable
fun TabProductItem(
    tabs: List<String>,
    productModel: ProductModel,
    similarProduct: List<ProductModel>,
    selected: Int,
    tabSelection: (Int) -> Unit,
    similarOnClick: (product: ProductModel) -> Unit = {},
    addOnClick: (ProductModel) -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        tabs.forEachIndexed { index, string ->
            val currentIndex = selected == index

            val containerColor by animateColorAsState(
                targetValue = if (currentIndex)
                    BackgroundCircleColor
                else BackgroundCardColor,
                animationSpec = tween(500)
            )
            val borderColor by animateColorAsState(
                targetValue = if (currentIndex) Color.Red else Color.Transparent,
                animationSpec = tween(500)
            )
            val textColor by animateColorAsState(
                targetValue = if (selected == index) ButtonColor_Tow else Color(0XFF938F8C),
                animationSpec = tween(500)
            )



            Card(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { tabSelection(index) }
                    .padding(horizontal = 3.dp)
                    .border(
                        BorderStroke(
                            1.dp,
                            borderColor
                        ),
                        shape = CircleShape
                    ),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = containerColor
                ),

                ) {
                Text(
                    string,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(10.dp),
                    color = textColor
                )
            }
        }
    }

    when (selected) {
        0 -> {
            SpacerHeight(20)
            Description { productModel.desc }
        }

        1 -> {
            SpacerHeight(20)
            Features(productModel.features)
        }

        2 -> {
            SpacerHeight(20)
            Comment(productModel.comments)
        }

        3 -> {

            SimilarProduct(
                similarProduct,
                similarOnClick = {
                    similarOnClick(it)
                }) {
                addOnClick(it)
            }
        }
    }

}

@Composable
private fun Description(
    text: () -> String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            "توضیحات",
            style = MaterialTheme.typography.titleSmall
        )
        SpacerHeight(20)

        Text(
            text(),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
private fun Features(
    features: List<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        item {
            Text(
                "ویژگی ها",
                style = MaterialTheme.typography.titleSmall
            )
            SpacerHeight(20)
        }

        items(features.size) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .background(
                        color = if (it % 2 == 0)
                            Color.LightGray
                        else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    features[it],
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun Comment(
    comment: List<CommentModel>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(comment.size) {

            val currentComment = comment[it]

            Row(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    currentComment.name,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                )

                Column {
                    Icon(
                        Icons.Default.Star,
                        null,
                        tint = Color(0XFFFFB800)
                    )

                    Text(
                        currentComment.rate.toPersianDigit(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                }

            }

            SpacerHeight(10)
            Text(
                currentComment.text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier
                    .padding(horizontal = 70.dp, vertical = 20.dp)
            )
        }
    }
}

@Composable
private fun SimilarProduct(
    products: List<ProductModel>,
    similarOnClick: (product: ProductModel) -> Unit = {},
    addOnClick: (ProductModel) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        items(products.size) {
            ProductItemSample(
                products[it],
                onClick = { similarOnClick(products[it]) }
            ) {
                addOnClick(products[it])
            }
        }
    }

}