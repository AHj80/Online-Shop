package com.ahj.onlineshop.feature.product.presentation.subCategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.core.sharedData.product.domain.useCase.AddToCartUseCase
import com.ahj.onlineshop.feature.product.domain.usecase.GetSubCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _uiState = MutableStateFlow(SubCategoriesUiState())
    val uiState: StateFlow<SubCategoriesUiState> = _uiState.asStateFlow()

    private val _arg = savedStateHandle.toRoute<Screens.SubCategory>()

    init {
        saveStateData()
    }

    fun saveStateData() {
        val parentCategory = _arg.parentCategory
        checkCategory(parentCategory)
    }

    private fun subCategories(parentCategory: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(status = SubCategoriesStatus.LOADING, messageStatus = null) }

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
                            messageStatus = error.message
                        )
                    }
                }
        }
    }

    fun addToCart(productModel: ProductModel) {
        viewModelScope.launch {
            addToCartUseCase(productModel)
            _uiState.update { it.copy(message = "به سبد خرید اضافه گردید") }
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

    private fun checkCategory(parentCategory: String) {
        if (_uiState.value.selected.isBlank())
            selectedCategory(parentCategory)
        else
            subCategories(_uiState.value.selected)
    }

    fun changeModalState(state: Boolean) {
        _uiState.update { it.copy(showModal = state) }
    }

    fun resetSnackBar() {
        _uiState.update { it.copy(message = null) }
    }
}