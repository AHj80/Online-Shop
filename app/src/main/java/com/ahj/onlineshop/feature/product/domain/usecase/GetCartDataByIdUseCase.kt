package com.ahj.onlineshop.feature.product.domain.usecase

import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartDataByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(id: String): Flow<Boolean> {

        return repository.getCartDataById(id).map {
            it != null
        }
    }

}