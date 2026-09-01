package com.ahj.onlineshop.core.sharedData.product.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(productEntity: ProductEntity): Long


    @Query("SELECT * FROM ProductEntity WHERE id = :id LIMIT 1")
    fun getCartDataById(id: String): Flow<ProductEntity?>

    @Query("SELECT * FROM ProductEntity")
    fun getCartData(): Flow<List<ProductEntity>>

    @Query("UPDATE ProductEntity SET quantity = quantity +1 WHERE id = :id")
    suspend fun increaseQuantity(id: String)

    @Query("UPDATE ProductEntity SET quantity = quantity -1 WHERE id = :id And quantity > 1")
    suspend fun decreaseQuantity(id: String)

    @Query("DELETE FROM ProductEntity")
    suspend fun deleteAllRecord()

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

}

