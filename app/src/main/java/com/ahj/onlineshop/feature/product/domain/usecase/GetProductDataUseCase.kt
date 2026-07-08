package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetProductDataUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): Result<List<ProductModel>> {

        return repository.getProductData().fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(Exception("خطای : $it"))
            }
        )
    }


}