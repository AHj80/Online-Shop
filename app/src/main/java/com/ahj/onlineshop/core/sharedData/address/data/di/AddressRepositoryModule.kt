package com.ahj.onlineshop.core.sharedData.address.data.di

import com.ahj.onlineshop.core.sharedData.address.data.repository.AddressRepositoryImpl
import com.ahj.onlineshop.core.sharedData.address.domain.repository.AddressRepository
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