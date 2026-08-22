package com.ahj.onlineshop.core.sharedData.shoppingExperience.data.di

import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.repository.UserExperienceRepositoryImpl
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.repository.UserExperienceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UserExperienceRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindingExperienceRepository(
        repository: UserExperienceRepositoryImpl
    )  : UserExperienceRepository
}