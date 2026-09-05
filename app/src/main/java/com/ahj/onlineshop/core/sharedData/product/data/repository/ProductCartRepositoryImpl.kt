package com.ahj.onlineshop.core.sharedData.product.data.repository

import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.product.data.local.db.ProductDao
import com.ahj.onlineshop.core.sharedData.product.data.mapper.toAddCartModel
import com.ahj.onlineshop.core.sharedData.product.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.product.domain.model.AddCartModel
import com.ahj.onlineshop.core.sharedData.product.domain.repository.ProductCartRepository
import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.product.data.mapper.toEntity
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductCartRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val runCatching: RunCatching
) : ProductCartRepository{

    override suspend fun addProductToCart(product: ProductModel): Result<Long> =
        runCatching.safeData {
            productDao.addProductToCart(product.toEntity())
        }

    override fun getCartDataById(id: String): Flow<AddCartModel?> =

        productDao.getCartDataById(id)
            .map {
                it?.toAddCartModel()
            }
            .catch { e ->
                throw e
            }


    override fun getCartData(): Flow<Result<List<CartModel>>> =

        productDao.getCartData()
            .map {
                Result.success(
                    it.map { product ->
                        product.toDomain()
                    }
                )

            }
            .catch {
                emit(
                    Result.failure(Exception("خطای داخلی : ${it.message}"))
                )
            }


}