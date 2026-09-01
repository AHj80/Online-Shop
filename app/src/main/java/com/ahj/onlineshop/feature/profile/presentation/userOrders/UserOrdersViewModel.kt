package com.ahj.onlineshop.feature.profile.presentation.userOrders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.userOrders.domain.useCase.GetAllOrdersUseCase
import com.ahj.onlineshop.feature.profile.domain.model.OrdersDataModel
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class UserOrdersViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserOrdersUiState())
    val uiState: StateFlow<UserOrdersUiState> = _uiState.asStateFlow()


    init {
        getAllData()
    }
    fun getAllData() {
        _uiState.update {
            it.copy(
                status = UserOrdersStatus.LOADING,
                message = null
            )
        }
        combinedData().onEach { result ->
            result
                .onSuccess { data ->
                    if (data.order.isEmpty()){
                        _uiState.update {
                            it.copy(
                                status = UserOrdersStatus.EMPTY,
                                header = data.header,
                                ordersData = emptyList(),
                                message = "سفارشی یافت نشد...!"
                            )
                        }
                    } else{
                        _uiState.update {
                            it.copy(
                                status = UserOrdersStatus.SUCCESS,
                                header = data.header,
                                ordersData = data.order,
                                message = null
                            )
                        }
                    }

                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = UserOrdersStatus.ERROR,
                            message = error.message,
                        )
                    }

                }


        }.launchIn(viewModelScope)
    }

    private fun combinedData() =
        combine(
            getHeaderDataUseCase(),
            getAllOrdersUseCase()
        ) { headerRes, orderRes ->
            if (headerRes.isSuccess && orderRes.isSuccess) {
                Result.success(
                    OrdersDataModel(
                        orderRes.getOrThrow(),
                        headerRes.getOrThrow()
                    )
                )
            } else {
                val error = headerRes.exceptionOrNull() ?: orderRes.exceptionOrNull()
                ?: Exception("خطای داخلی")
                Result.failure(error)
            }

        }



}