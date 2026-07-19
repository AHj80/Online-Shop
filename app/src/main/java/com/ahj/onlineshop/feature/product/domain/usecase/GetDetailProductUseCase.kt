package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.DetailProductData
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetDetailProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(id: String , categoryType: String): Result<DetailProductData> {

        return repository.getDetailProductData(id).map {

            val filterSimilarData = it.similarData.filter { it.categoryType == categoryType && it.id != id }

            DetailProductData(it.productData , filterSimilarData)

        }
    }
}