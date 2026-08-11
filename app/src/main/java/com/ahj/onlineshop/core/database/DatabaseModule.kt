package com.ahj.onlineshop.core.database

import android.content.Context
import androidx.room.Room
import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.common.sharedData.product.local.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(
    SingletonComponent::class
)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(
            context = context,
            klass = MyDatabase::class.java,
            name = MyDatabase.ONLINE_SHOP_DATABASE
        ).build()

    @Singleton
    @Provides
    fun provideProductDao(myDatabase: MyDatabase): ProductDao =
        myDatabase.productDao()

    @Singleton
    @Provides
    fun provideAddressDao(myDatabase: MyDatabase): AddressDao =
        myDatabase.addressDao()


}