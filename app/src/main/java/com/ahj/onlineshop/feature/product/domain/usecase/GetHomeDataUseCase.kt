package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.model.HomeDataModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import javax.inject.Inject


class GetHomeDataUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke() =
        repository.getHomeData().map { data->
            val product = data.product.filter { it.sales>150 }

            HomeDataModel(
                product = product,
                banner = data.banner,
                category = data.category
            )
        }


}