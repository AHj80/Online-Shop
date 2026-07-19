package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.ShopData
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetProductByFilterUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(
        subCategory: String,
        parentCategory: String
    ): Result<ShopData> =

        repository.getShopData().map { data ->
            val product = data.product.filter { it.categoryType == subCategory }
            val subCategory = data.subCategories.filter { it.parentCategory == parentCategory }

            ShopData(
                data.categories,
                subCategory,
                product
            )

        }
}