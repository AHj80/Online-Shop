package com.ahj.onlineshop.core.sharedData.product.domain.useCase

import com.ahj.onlineshop.core.sharedData.product.domain.repository.ProductCartRepository
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: ProductCartRepository
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