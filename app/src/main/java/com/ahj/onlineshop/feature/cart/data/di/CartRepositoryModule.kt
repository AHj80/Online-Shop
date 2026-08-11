package com.ahj.onlineshop.feature.cart.data.di

import com.ahj.onlineshop.feature.cart.data.repository.CartRepositoryImpl
import com.ahj.onlineshop.feature.cart.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CartRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

}
