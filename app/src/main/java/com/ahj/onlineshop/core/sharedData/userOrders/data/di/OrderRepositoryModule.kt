package com.ahj.onlineshop.core.sharedData.userOrders.data.di

import com.ahj.onlineshop.core.sharedData.userOrders.data.repository.UserOrderRepositoryImpl
import com.ahj.onlineshop.core.sharedData.userOrders.domain.repository.UserOrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindingOrderRepository(
        repository: UserOrderRepositoryImpl
    ): UserOrderRepository

}