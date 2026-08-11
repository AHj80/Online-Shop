package com.ahj.onlineshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressDao
import com.ahj.onlineshop.core.common.sharedData.address.data.local.db.AddressEntity
import com.ahj.onlineshop.core.common.sharedData.product.local.db.ProductDao
import com.ahj.onlineshop.core.common.sharedData.product.local.db.ProductEntity

@Database(
    entities = [ProductEntity::class , AddressEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        const val ONLINE_SHOP_DATABASE = "online_shop_database"
    }

    abstract fun productDao(): ProductDao

    abstract fun addressDao(): AddressDao
}