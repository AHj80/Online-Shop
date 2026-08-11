package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class AddToCartUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productModel: ProductModel): Result<Long> =
        repository.addProductToCart(productModel).fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(Exception("خطای داخلی: ${it.message}"))
            }
        )
}