package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.common.utils.PersianDate
import com.ahj.onlineshop.core.common.utils.formatPriceToPersian
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAddressByIdUseCase
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.OrderItemModel
import com.ahj.onlineshop.core.sharedData.userOrders.domain.model.UserOrderModel
import com.ahj.onlineshop.feature.cart.domain.model.CartAddressModel
import com.ahj.onlineshop.core.sharedData.product.domain.useCase.GetCartDataUseCase
import com.ahj.onlineshop.feature.cart.domain.useCase.PaymentProcessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressConfirmViewModel @Inject constructor(
    private val getAddressByIdUseCase: GetAddressByIdUseCase,
    private val getCartDataUseCase: GetCartDataUseCase,
    private val paymentProcessUseCase: PaymentProcessUseCase,
    private val persianDate: PersianDate,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressConfirmUiState())
    val uiState: StateFlow<AddressConfirmUiState> = _uiState.asStateFlow()


    init {
        getDefaultAddress()
    }

    fun getDataAddress(id: Int?) {

        combinedData(id).onEach { result ->
            _uiState.update {
                it.copy(
                    status = AddressConfirmStatus.LOADING,
                    message = null
                )
            }
            result.onSuccess { dataAddress ->

                if (dataAddress.address.phone.isBlank()) {
                    _uiState.update {
                        it.copy(
                            status = AddressConfirmStatus.EMPTY,
                            data = dataAddress.cartCalculation,
                            message = "لطفا آدرس جدیدی اضافه نمایید"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            status = AddressConfirmStatus.SUCCESS,
                            data = dataAddress.cartCalculation,
                            address = dataAddress.address,
                            message = null
                        )

                    }
                }

            }
            result.onFailure { error ->

                _uiState.update {
                    it.copy(
                        status = AddressConfirmStatus.ERROR,
                        message = error.message
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun combinedData(id: Int?) =
        combine(
            getCartDataUseCase(),
            getAddressByIdUseCase(id)
        ) { dataRes, addressRes ->
            if (dataRes.isSuccess && addressRes.isSuccess) {
                Result.success(
                    CartAddressModel(
                        dataRes.getOrThrow(),
                        addressRes.getOrThrow()
                    )
                )
            } else Result.failure(Exception("-1 خطای داخلی"))
        }


    fun getDefaultAddress() {
        sessionManager.defaultAddress.onEach { address ->
            _uiState.update { it.copy(defaultAddress = address) }
        }.launchIn(viewModelScope)
    }


    fun buyProducts() {
        _uiState.update { it.copy(orderResult = listOf(true, false, true).random()) }
        val state = _uiState.value
        val cartCalculation = state.data ?: return
        val order = cartCalculation.item.map {
            OrderItemModel(
                null,
                productId = it.id,
                image = it.image,
                title = it.title,
                quantity = it.quantity,
                categoryType = it.categoryType
            )
        }

        val userOrder = UserOrderModel(
            date = persianDate.fromTimestamp().getCustomDate(),
            orderResult = state.orderResult,
            orderCode = state.orderCode,
            orderPrice = "${cartCalculation.cartFinalPrice.formatPriceToPersian()} تومان ",
            item = order
        )
        viewModelScope.launch {
            paymentProcessUseCase(userOrder)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            resultPaymentDialog = true,
                            paymentText = "پرداخت و ثبت سفارش با موفقیت انجام شد، مراحل بعدی سفارش از طریق پیامک به شماره تماس گیرنده آدرس اطلاع رسانی خواهد شد",

                            )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            resultPaymentDialog = true,
                            paymentText = "${error.message}"
                        )
                    }
                }
        }

    }

    fun changePaymentDialog() =
        _uiState.update {
            it.copy(
                resultPaymentDialog = false,
                paymentText = null,
                navigating = it.orderResult
            )
        }
}