package com.ahj.onlineshop.core.network

import com.ahj.onlineshop.core.sharedData.userProfile.data.local.remote.ProfileApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserProfileModule {


    @Singleton
    @Provides
    fun provideUserProfileApiService(retrofit: Retrofit): ProfileApiService =
        retrofit.create(ProfileApiService::class.java)
}
