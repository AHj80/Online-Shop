package com.ahj.onlineshop.feature.cart.data.repository

import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.common.sharedData.product.local.db.ProductDao
import com.ahj.onlineshop.feature.cart.data.mapper.toCartEntity
import com.ahj.onlineshop.feature.cart.data.mapper.toModel
import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val runCatching: RunCatching,
    private val productDao: ProductDao,

) : CartRepository {

    override fun getCartData(): Flow<Result<List<CartModel>>> =

        productDao.getCartData()
            .map {
                Result.success(
                    it.map { product ->
                        product.toModel()
                    }
                )

            }
            .catch {
                emit(
                    Result.failure(Exception("خطای داخلی : ${it.message}"))
                )
            }



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


}
