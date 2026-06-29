package com.ahj.onlineshop.core.common.dispathers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): CoroutineDispatchers = CoroutineDispatchers()
}