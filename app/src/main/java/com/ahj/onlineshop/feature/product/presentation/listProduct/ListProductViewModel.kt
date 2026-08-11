package com.ahj.onlineshop.feature.product.presentation.listProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.usecase.AddToCartUseCase
import com.ahj.onlineshop.feature.product.domain.usecase.GetProductByFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListProductViewModel @Inject constructor(
    private val getProductByFilterUseCase: GetProductByFilterUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListProductUiState())
    val uiState: StateFlow<ListProductUiState> = _uiState.asStateFlow()


    fun getData(productType: String, parentCategory: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(status = ListProductStatus.LOADING, message = null) }
            getProductByFilterUseCase(productType, parentCategory)
                .onSuccess { data ->

                    _uiState.update {
                        it.copy(
                            status = ListProductStatus.SUCCESS,
                            product = data.product,
                            subCategory = data.subCategories,
                            category = data.categories
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = ListProductStatus.ERROR,
                            message = error.message
                        )
                    }
                }
        }
    }

    fun updateText(text: String) = _uiState.update { it.copy(stateSearch = text) }

    fun selectedItem(subCategory: String, parentCategory: String) {
        _uiState.update { it.copy(selected = subCategory) }
        getData(subCategory, parentCategory)
    }
    fun checkCategory(productType: String, parentCategory: String) {

        if (_uiState.value.selected.isBlank()) {
            selectedItem(productType , parentCategory)
        } else {
            getData(_uiState.value.selected, parentCategory)
        }
    }

    fun addToCart(productModel: ProductModel){
        viewModelScope.launch {
            addToCartUseCase(productModel)
        }
    }
}