package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.SubCategoriesData
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetSubCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(parentCategory: String): Result<SubCategoriesData> =

        repository.getSubCategoriesData().map { data ->
            val resultSubCategory = data.subCategories.filter { it.parentCategory == parentCategory }
            val resultBestSales = data.bestSales.filter { it.sales > 10 }

            SubCategoriesData(
                data.categories,
                resultSubCategory,
                resultBestSales
            )
        }
}