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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahj.onlineshop.core.common.ui.component.InsertButtonSecondary
import com.ahj.onlineshop.core.common.ui.component.SpacerHeight
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCardColor
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import com.ahj.onlineshop.core.common.ui.theme.ButtonColor_Tow
import com.ahj.onlineshop.core.common.utils.toPersianDigit
import com.ahj.onlineshop.feature.authentication.component.InsertBody
import com.ahj.onlineshop.feature.product.domain.model.CommentModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel

@Composable
fun TabProductItem(
    tabs: List<String>,
    productModel: ProductModel,
    similarProduct: List<ProductModel>,
    selected: Int,
    stateTextComment: String,
    rate: (Int) -> Unit,
    tabSelection: (Int) -> Unit,
    similarOnClick: (product: ProductModel) -> Unit = {},
    stateTextChange: (String) -> Unit,
    sendComment: () -> Unit,
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
            Comment(
                productModel.comments,
                stateTextComment,
                rate = { rate(it) },
                { stateTextChange(it) },
                sendComment = { sendComment() }
            )
        }

        3 -> {

            SimilarProduct(
                products = similarProduct,
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
    comment: List<CommentModel>,
    stateText: String,
    rate: (Int) -> Unit,
    stateTextChange: (String) -> Unit,
    sendComment: () -> Unit = {}
) {
    var state by rememberSaveable { mutableIntStateOf(1) }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {

            Text(
                "ثبت نظر",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                TextField(
                    value = stateText,
                    onValueChange = { stateTextChange(it) },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(150.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = BackgroundCardColor,
                        unfocusedContainerColor = Color.LightGray,
                        focusedContainerColor = BackgroundCardColor,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { InsertBody("نظر خود را وارد نمایید") },
                    textStyle = MaterialTheme.typography.bodyMedium,

                    )
                SpacerHeight(10)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "امتیاز دهید",
                            style = MaterialTheme.typography.titleSmall,
                        )
                        for (i in 5 downTo 1) {
                            val isSelected = i <= state

                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "امتیاز $i",
                                tint = if (isSelected) Color(0xFFFFB800) else Color.LightGray,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        state = i
                                        rate(state)
                                    }
                            )
                        }
                    }
                    InsertButtonSecondary(
                        text = "ارسال",
                        fontSize = 10,
                        enabled = stateText.isNotBlank(),
                        modifier = Modifier.width(80.dp)
                    ) { sendComment() }


                }
            }

        }
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
                productModel = products[it],
                onClick = { similarOnClick(products[it]) },
            ) {
                addOnClick(products[it])
            }
        }
    }

}