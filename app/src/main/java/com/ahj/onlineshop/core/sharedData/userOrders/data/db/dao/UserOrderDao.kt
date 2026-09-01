package com.ahj.onlineshop.core.sharedData.userOrders.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.OrderItemEntity
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.OrderWithItem
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.entity.UserOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserOrderDao {

    @Insert
    suspend fun addOrder(order: UserOrderEntity): Long

    @Insert
    suspend fun addOrderItems(items: List<OrderItemEntity>)

    @Transaction
    suspend fun createOrderWithItems(order: UserOrderEntity, items: List<OrderItemEntity>) {

        val insertOrder = addOrder(order)

        val insertItems = items.map { itemEntity ->
            itemEntity.copy(orderId = insertOrder)
        }
        addOrderItems(insertItems)

    }

    @Transaction
    @Query("SELECT * FROM Userorderentity ORDER BY id DESC")
    fun getAllOrder(): Flow<List<OrderWithItem>>

}