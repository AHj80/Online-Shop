package com.ahj.onlineshop.core.sharedData.favorite.data.di

import com.ahj.onlineshop.core.sharedData.favorite.data.repository.FavoriteRepositoryImpl
import com.ahj.onlineshop.core.sharedData.favorite.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindingFavoriteRepository(
        repositoryModule: FavoriteRepositoryImpl
    ) : FavoriteRepository

}