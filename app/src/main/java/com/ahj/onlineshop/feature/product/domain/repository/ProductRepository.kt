package com.ahj.onlineshop.feature.product.domain.repository

import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.HomeDataModel
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.model.ShopData
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel

interface ProductRepository {

    suspend fun getProductData(): Result<List<ProductModel>>

   suspend fun getCategories(): Result<List<CategoryModel>>

    suspend fun getBanner(): Result<List<BannerModel>>

    suspend fun getHomeData(): Result<HomeDataModel>

    suspend fun getSubCategories(): Result<List<SubCategoryModel>>

    suspend fun getShopData(): Result<ShopData>

}