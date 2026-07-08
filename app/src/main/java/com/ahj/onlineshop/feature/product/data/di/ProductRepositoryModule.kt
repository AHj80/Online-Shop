package com.ahj.onlineshop.feature.product.data.di

import com.ahj.onlineshop.feature.product.data.repository.ProductRepositoryImpl
import com.ahj.onlineshop.feature.product.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ProductRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}