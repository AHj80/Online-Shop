package com.ahj.onlineshop.feature.product.presentation.subCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.usecase.AddToCartUseCase
import com.ahj.onlineshop.feature.product.domain.usecase.GetSubCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SubCategoriesUiState())
    val uiState: StateFlow<SubCategoriesUiState> = _uiState


    fun subCategories(parentCategory: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(status = SubCategoriesStatus.LOADING, message = null) }

            getSubCategoriesUseCase(parentCategory)
                .onSuccess { data ->

                    _uiState.update {
                        it.copy(
                            status = SubCategoriesStatus.SUCCESS,
                            categories = data.categories,
                            product = data.product,
                            subCategories = data.subCategories
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = SubCategoriesStatus.ERROR,
                            message = error.message
                        )
                    }
                }
        }
    }

    fun addToCart(productModel: ProductModel){
        viewModelScope.launch {
            addToCartUseCase(productModel)
        }
    }
    fun selectedCategory(categoryType: String) {

        _uiState.update {
            it.copy(
                selected = categoryType
            )
        }

        subCategories(categoryType)

    }

    fun updateText(text: String) = _uiState.update { it.copy(stateText = text) }

    fun checkCategory(parentCategory: String) {
        if (_uiState.value.selected.isBlank())
            selectedCategory(parentCategory)
        else
            subCategories(_uiState.value.selected)
    }

    fun changeModalState(state: Boolean) {
        _uiState.update { it.copy(showModal = state) }
    }

}