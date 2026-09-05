package com.ahj.onlineshop.feature.cart.data.repository

import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.product.data.local.db.ProductDao
import com.ahj.onlineshop.feature.cart.data.mapper.toCartEntity
import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val runCatching: RunCatching,
    private val productDao: ProductDao

) : CartRepository {


    override suspend fun increaseQuantity(id: String): Result<Boolean> =
        runCatching.safeData {
            productDao.increaseQuantity(id)
            true
        }

    override suspend fun decreaseQuantity(id: String): Result<Boolean> =
        runCatching.safeData {
            productDao.decreaseQuantity(id)
            true
        }

    override suspend fun deleteProduct(product: CartModel): Result<Boolean> =
        runCatching.safeData {
            productDao.deleteProduct(product.toCartEntity())
            true
        }

    override suspend fun deleteAllRecord(): Result<Boolean> =
        runCatching.safeData {
            productDao.deleteAllRecord()
            true
        }


}
