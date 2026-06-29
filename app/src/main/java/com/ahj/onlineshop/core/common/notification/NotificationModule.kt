package com.ahj.onlineshop.core.common.notification

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Singleton
    @Binds
    abstract fun provideNotificationController(
        config: NotificationConfig
    ) : NotificationImpl

}