package com.ahj.onlineshop.core.sharedData.userOrders.data.repository

import com.ahj.onlineshop.core.common.utils.RunCatching
import com.ahj.onlineshop.core.sharedData.userOrders.data.db.dao.UserOrderDao
import com.ahj.onlineshop.core.sharedData.userOrders.data.mapper.toDomain
import com.ahj.onlineshop.core.sharedData.userOrders.data.mapper.toEntity
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.repository.UserOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserOrderRepositoryImpl @Inject constructor(
    private val runCatching: RunCatching,
    private val userOrderDao: UserOrderDao,

    ) : UserOrderRepository {

    override suspend fun insertOrders(
        order: UserOrderModel
    ): Result<Boolean> =

        runCatching.safeData {

            userOrderDao.createOrderWithItems(
                order.toEntity(),
                order.item.map { it.toEntity() }
            )
            true
        }


    override fun getAllOrders(): Flow<Result<List<UserOrderModel>>> =

        userOrderDao.getAllOrder().map { list ->
            runCatching.safeData {
                list.map { order ->
                    order.toDomain()
                }
            }
        }

}