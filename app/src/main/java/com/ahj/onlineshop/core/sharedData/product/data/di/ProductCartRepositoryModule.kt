package com.ahj.onlineshop.core.sharedData.product.data.di

import com.ahj.onlineshop.core.sharedData.product.data.repository.ProductCartRepositoryImpl
import com.ahj.onlineshop.core.sharedData.product.domain.repository.ProductCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ProductCartRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductCartRepository(
        repository: ProductCartRepositoryImpl
    )  : ProductCartRepository
}