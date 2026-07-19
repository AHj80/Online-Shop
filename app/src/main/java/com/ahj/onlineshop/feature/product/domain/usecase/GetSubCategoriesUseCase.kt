package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.ShopData
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetSubCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(parentCategory: String): Result<ShopData> =

        repository.getShopData().map { data ->
            val resultSubCategory =
                data.subCategories.filter { it.parentCategory == parentCategory }
            val resultBestSales = data.product.filter { it.sales > 150 }

            ShopData(
                data.categories,
                resultSubCategory,
                resultBestSales
            )
        }
}