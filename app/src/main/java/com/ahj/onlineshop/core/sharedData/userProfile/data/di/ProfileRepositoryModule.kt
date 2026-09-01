package com.ahj.onlineshop.core.sharedData.userProfile.data.di

import com.ahj.onlineshop.core.sharedData.userProfile.data.repository.ProfileRepositoryImpl
import com.ahj.onlineshop.core.sharedData.userProfile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProfileRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepositoryImpl(
        repositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}