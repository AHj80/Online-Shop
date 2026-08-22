package com.ahj.onlineshop.feature.cart.presentation.addressConfirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAddressByIdUseCase
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.feature.cart.domain.model.CartAddressModel
import com.ahj.onlineshop.feature.cart.domain.useCase.GetCartDataUseCase
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
class AddressConfirmViewModel @Inject constructor(
    private val getAddressByIdUseCase: GetAddressByIdUseCase,
    private val getCartDataUseCase: GetCartDataUseCase,
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

    fun combinedData(id: Int?) =
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
}