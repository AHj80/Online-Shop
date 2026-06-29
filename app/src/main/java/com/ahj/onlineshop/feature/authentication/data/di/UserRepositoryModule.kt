package com.ahj.onlineshop.feature.authentication.data.di

import com.ahj.onlineshop.feature.authentication.data.repository.UserRepositoryImpl
import com.ahj.onlineshop.feature.authentication.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun userRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}