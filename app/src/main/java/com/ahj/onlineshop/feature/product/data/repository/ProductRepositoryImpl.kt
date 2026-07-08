package com.ahj.onlineshop.feature.product.data.repository

import com.ahj.onlineshop.core.common.utils.ApiHelper
import com.ahj.onlineshop.core.common.utils.ApiHelperOffline
import com.ahj.onlineshop.feature.product.data.local.offlineData.OfflineData
import com.ahj.onlineshop.feature.product.data.mapper.toModel
import com.ahj.onlineshop.feature.product.data.remote.ProductApiService
import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.HomeDataModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.model.SubCategoriesData
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val productApiService: ProductApiService,
    private val apiHelperOffline: ApiHelperOffline
) : ProductRepository {


    override suspend fun getProductData(): Result<List<ProductModel>> =
        apiHelper.safeData { productApiService.getProductsData() }
            .map { listData -> listData?.map { it.toModel() } ?: emptyList() }


    override suspend fun getCategories(): Result<List<CategoryModel>> =
        apiHelperOffline.safeData {
            OfflineData.category
        }


    override suspend fun getBanner(): Result<List<BannerModel>> =
        apiHelperOffline.safeData {
            OfflineData.listBanner
        }


    override suspend fun getHomeData(): Result<HomeDataModel> =

        apiHelperOffline.safeData {
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
        apiHelperOffline.safeData { OfflineData.listSubCategory }

    override suspend fun getSubCategoriesData(): Result<SubCategoriesData> =
        apiHelperOffline.safeData {
            coroutineScope {
                val productReq = async { getProductData() }
                val categoryReq = async { getCategories() }
                val subCategoryReq = async { getSubCategories() }

                val productRes = productReq.await().getOrThrow()
                val categoryRes = categoryReq.await().getOrThrow()
                val subCategoryRes = subCategoryReq.await().getOrThrow()

                SubCategoriesData(
                    categoryRes,
                    subCategoryRes,
                    productRes
                )
            }
        }


}