package com.ahj.onlineshop.feature.cart.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.cart.domain.model.CartModel
import com.ahj.onlineshop.feature.cart.domain.useCase.DecreaseQuantityUseCase
import com.ahj.onlineshop.feature.cart.domain.useCase.GetCartDataUseCase
import com.ahj.onlineshop.feature.cart.domain.useCase.IncreaseQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartDataUseCase: GetCartDataUseCase,
    private val increaseQuantityUseCase: IncreaseQuantityUseCase,
    private val decreaseQuantityUseCase: DecreaseQuantityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        getCartData()
    }

    fun getCartData() {
        _uiState.update { it.copy(cartStatus = CartStatus.LOADING, message = null) }
        getCartDataUseCase()
            .onEach { result ->
                result.onSuccess { data ->
                    if (data.item.isEmpty()){
                        _uiState.update {
                            it.copy(
                                message = "سبد خرید شما خالی است!",
                                cartStatus = CartStatus.EMPTY
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                cartStatus = CartStatus.SUCCESS,
                                data = data.item,
                                price = data.cartPrice,
                                finalPrice = data.cartFinalPrice,
                                discount = data.cartDiscount
                            )
                        }
                    }

                }
                result.onFailure { error ->
                    _uiState.update {
                        it.copy(
                            cartStatus = CartStatus.ERROR,
                            message = error.message
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }

    fun increaseQuantity(id: String) {
        viewModelScope.launch {
            increaseQuantityUseCase(id)
                .onSuccess {
                    _uiState.update { it.copy(message = "یک آیتم به سبد خرید شما اضافه شد") }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }
                }
        }
    }

    fun decreaseQuantity(id: String, quantity: Int, currentProduct: CartModel) {
        viewModelScope.launch {
            decreaseQuantityUseCase(id,quantity,currentProduct)
            _uiState.update { it.copy(message = "یک آیتم از سبد خرید شما کسر شد") }
        }
    }


}