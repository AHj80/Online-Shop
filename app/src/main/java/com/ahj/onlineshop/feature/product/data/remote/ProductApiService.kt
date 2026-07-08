package com.ahj.onlineshop.feature.product.data.remote

import com.ahj.onlineshop.feature.product.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET


interface ProductApiService {

    @GET("products")
    suspend fun getProductsData(): Response<List<ProductDto>>
}