package com.ahj.onlineshop.feature.product.presentation.listProduct

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.core.sharedData.product.domain.useCase.AddToCartUseCase
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
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
    private val addToCartUseCase: AddToCartUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _args = savedStateHandle.toRoute<Screens.ListProductScreen>()
    private val _uiState = MutableStateFlow(
        ListProductUiState(
            selected = _args.subCategoryType,
            parentCategory = _args.parentCategory
        )
    )
    val uiState: StateFlow<ListProductUiState> = _uiState.asStateFlow()

    init {
        saveStateData()
    }

   fun saveStateData() {
        checkCategory(_uiState.value.selected, _uiState.value.parentCategory)
    }

    private fun getData(productType: String, parentCategory: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(status = ListProductStatus.LOADING, messageStatus = null) }
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
                            messageStatus = error.message
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
            selectedItem(productType, parentCategory)
        } else {
            getData(_uiState.value.selected, parentCategory)
        }
    }

    fun addToCart(productModel: ProductModel) {
        viewModelScope.launch {
            addToCartUseCase(productModel)
            _uiState.update { it.copy(message = "به سبد خرید اضافه گردید") }
        }
    }

    fun resetSnackBar() {
        _uiState.update { it.copy(message = null) }
    }
}