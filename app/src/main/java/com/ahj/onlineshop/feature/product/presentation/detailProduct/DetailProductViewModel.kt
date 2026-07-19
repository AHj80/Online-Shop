package com.ahj.onlineshop.feature.product.presentation.detailProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.product.domain.usecase.GetDetailProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val getDetailProductUseCase: GetDetailProductUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailProductUiState())
    val uiState: StateFlow<DetailProductUiState> = _uiState.asStateFlow()

    fun getProduct(id: String, categoryType: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(status = DetailProductStatus.LOADING, message = null) }
            getDetailProductUseCase(id, categoryType)
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            product = data.productData,
                            similarProduct = data.similarData,
                            status = DetailProductStatus.SUCCESS
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(
                        status = DetailProductStatus.ERROR,
                        message = error.message,
                    ) }
                }
        }
    }

    fun increaseQuantity() {
        if (_uiState.value.quantity < 4) _uiState.update { it.copy(quantity = _uiState.value.quantity + 1) }
        else
            _uiState.update { it.copy(message = "تعداد درخواست شده بیش از میزان موجودی میباشد") }
    }

    fun decreaseQuantity() {
        if (_uiState.value.quantity > 0)
            _uiState.update { it.copy(quantity = (_uiState.value.quantity - 1).coerceAtLeast(1)) }
    }

    fun changeTab(index: Int) = _uiState.update { it.copy(selectedTab = index) }

}