package com.ahj.onlineshop.feature.profile.data.di

import com.ahj.onlineshop.feature.profile.data.repository.ProfileRepositoryImpl
import com.ahj.onlineshop.feature.profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepositoryImpl(
        repositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}