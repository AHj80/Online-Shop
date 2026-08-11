package com.ahj.onlineshop.feature.product.data.repository

import com.ahj.onlineshop.core.common.utils.ApiHelper
import com.ahj.onlineshop.core.common.utils.ApiHelperAsync
import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.common.sharedData.product.local.db.ProductDao
import com.ahj.onlineshop.feature.product.data.local.offlineData.OfflineData
import com.ahj.onlineshop.feature.product.data.mapper.toEntity
import com.ahj.onlineshop.feature.product.data.mapper.toAddCartModel
import com.ahj.onlineshop.feature.product.data.mapper.toProductModel
import com.ahj.onlineshop.feature.product.data.remote.ProductApiService
import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.DetailProductData
import com.ahj.onlineshop.feature.product.domain.model.HomeDataModel
import com.ahj.onlineshop.feature.product.domain.model.AddCartModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.model.ShopData
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val apiHelperAsync: ApiHelperAsync,
    private val productApiService: ProductApiService,
    private val productDao: ProductDao,
    private val runCatching: RunCatching
) : ProductRepository {
    override suspend fun getProductData(): Result<List<ProductModel>> =
        apiHelper.safeData { productApiService.getProductsData() }
            .map { listData -> listData?.map { it.toProductModel() } ?: emptyList() }


    override suspend fun getProductById(id: String): Result<ProductModel> =

        apiHelper.safeData { productApiService.getProductById(id) }
            .map {
                it?.firstOrNull()?.toProductModel() ?: ProductModel.empty()
            }


    override suspend fun getDetailProductData(id: String): Result<DetailProductData> =

        apiHelperAsync.safeData {
            coroutineScope {
                val productReq = async { getProductById(id) }
                val listProductReq = async { getProductData() }

                val productRes = productReq.await().getOrThrow()
                val listProductRes = listProductReq.await().getOrThrow()

                DetailProductData(productRes, listProductRes)
            }
        }

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


    override suspend fun getCategories(): Result<List<CategoryModel>> =
        apiHelperAsync.safeData {
            OfflineData.category
        }


    override suspend fun getBanner(): Result<List<BannerModel>> =
        apiHelperAsync.safeData {
            OfflineData.listBanner
        }

    override suspend fun getHomeData(): Result<HomeDataModel> =

        apiHelperAsync.safeData {
            coroutineScope {
                val productReq = async { getProductData() }
                val bannerReq = async { getBanner() }
                val categoryReq = async { getCategories() }

                val productRes = productReq.await().getOrThrow()
                val bannerRes = bannerReq.await().getOrThrow()
                val categoryRes = categoryReq.await().getOrThrow()

                HomeDataModel(
                    productRes,
                    bannerRes,
                    categoryRes
                )

            }

        }

    override suspend fun getSubCategories(): Result<List<SubCategoryModel>> =
        apiHelperAsync.safeData { OfflineData.listSubCategory }

    override suspend fun getShopData(): Result<ShopData> =
        apiHelperAsync.safeData {
            coroutineScope {
                val productReq = async { getProductData() }
                val categoryReq = async { getCategories() }
                val subCategoryReq = async { getSubCategories() }

                val productRes = productReq.await().getOrThrow()
                val categoryRes = categoryReq.await().getOrThrow()
                val subCategoryRes = subCategoryReq.await().getOrThrow()

                ShopData(
                    categoryRes,
                    subCategoryRes,
                    productRes
                )
            }
        }


}