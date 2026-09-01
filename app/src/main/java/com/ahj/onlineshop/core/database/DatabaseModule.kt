package com.ahj.onlineshop.core.database

import android.content.Context
import androidx.room.Room
import com.ahj.onlineshop.core.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.sharedData.favorite.data.local.db.FavoriteDao
import com.ahj.onlineshop.core.sharedData.product.local.db.ProductDao
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db.UserExperienceDao
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.dao.UserOrderDao
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.db.UserInformationDao
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

    @Singleton
    @Provides
    fun provideFavoriteDao(myDatabase: MyDatabase): FavoriteDao =
        myDatabase.favoriteDao()

    @Provides
    @Singleton
    fun provideUserExperienceDao(myDatabase: MyDatabase): UserExperienceDao =
        myDatabase.experienceDao()

    @Provides
    @Singleton
    fun provideUserOrders(myDatabase: MyDatabase): UserOrderDao =
        myDatabase.userOrdersDao()


    @Provides
    @Singleton
    fun provideEditUserProfileDao(myDatabase: MyDatabase): UserInformationDao =
        myDatabase.editUserProfileDao()


}