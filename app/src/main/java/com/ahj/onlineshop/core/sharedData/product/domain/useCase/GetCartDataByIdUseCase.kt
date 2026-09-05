package com.ahj.onlineshop.core.sharedData.product.domain.useCase

import com.ahj.onlineshop.core.sharedData.product.domain.repository.ProductCartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartDataByIdUseCase @Inject constructor(
    private val repository: ProductCartRepository
) {

    operator fun invoke(id: String): Flow<Boolean> {

        return repository.getCartDataById(id).map {
            it != null
        }
    }

}