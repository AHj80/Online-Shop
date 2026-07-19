package com.ahj.onlineshop.feature.product.data.remote


import com.ahj.onlineshop.feature.product.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductApiService {

    @GET("products")
    suspend fun getProductsData(): Response<List<ProductDto>>

    @GET("products")
    suspend fun getProductById(
        @Query("id")id: String
    ): Response<List<ProductDto>>
}