package com.ahj.onlineshop.core.common.sharedData.address.data.di

import com.ahj.onlineshop.core.common.sharedData.address.data.repository.AddressRepositoryImpl
import com.ahj.onlineshop.core.common.sharedData.address.domain.repository.AddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AddressRepositoryModule {

    @Singleton
    @Binds
    abstract fun addressRepositoryBind(
        repository: AddressRepositoryImpl
    ): AddressRepository

}