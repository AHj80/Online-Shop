package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> =
        repository.getCategories().fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(Exception("عملیات دریافت همراه با خطا بود:${it.message}"))
            }
        )
}