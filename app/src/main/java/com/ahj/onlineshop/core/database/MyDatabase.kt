package com.ahj.onlineshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahj.onlineshop.core.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.sharedData.address.data.local.db.AddressEntity
import com.ahj.onlineshop.core.sharedData.favorite.data.local.db.FavoriteDao
import com.ahj.onlineshop.core.sharedData.favorite.data.local.db.FavoriteEntity
import com.ahj.onlineshop.core.sharedData.product.data.local.db.ProductDao
import com.ahj.onlineshop.core.sharedData.product.data.local.db.ProductEntity
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db.UserExperienceDao
import com.ahj.onlineshop.core.sharedData.shoppingExperience.data.db.UserExperienceEntity
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.dao.UserOrderDao
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.OrderItemEntity
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.UserOrderEntity
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.db.UserInformationDao
import com.ahj.onlineshop.core.sharedData.userProfile.data.local.db.UserInformationEntity

@Database(
    entities = [
        ProductEntity::class,
        AddressEntity::class,
        FavoriteEntity::class,
        UserExperienceEntity::class,
        UserOrderEntity::class,
        OrderItemEntity::class,
        UserInformationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        const val ONLINE_SHOP_DATABASE = "online_shop_database"
    }

    abstract fun productDao(): ProductDao

    abstract fun addressDao(): AddressDao

    abstract fun favoriteDao(): FavoriteDao

    abstract fun experienceDao(): UserExperienceDao

    abstract fun userOrdersDao(): UserOrderDao

    abstract fun editUserProfileDao(): UserInformationDao
}