package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val repository: ProductRepository
) {

  suspend operator fun invoke(): Result<List<BannerModel>> =
        repository.getBanner().fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(Exception("عملیات دریافت همراه با خطا بود:${it.message}"))
            }
        )

}