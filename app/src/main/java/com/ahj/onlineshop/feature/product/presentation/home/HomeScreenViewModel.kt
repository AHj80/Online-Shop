package com.ahj.onlineshop.feature.product.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.usecase.AddToCartUseCase
import com.ahj.onlineshop.feature.product.domain.usecase.GetHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()


    init {
        getHomeData()
        expandedChange()
    }
    fun getHomeData() {

        viewModelScope.launch {
            _uiState.update { it.copy(status = HomeScreenStatus.LOADING, message = null) }
            getHomeDataUseCase()
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            status = HomeScreenStatus.SUCCESS,
                            data = data.product,
                            banner = data.banner,
                            categories = data.category
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = HomeScreenStatus.ERROR,
                            message = error.message
                        )
                    }
                }
        }

    }

    fun expandedChange(){
            _uiState.update { it.copy(expanded = false) }
    }
    fun updateText(text: String) =
        _uiState.update { it.copy(stateSearch = text , expanded = true) }

    fun changeModalState(state: Boolean){
        _uiState.update { it.copy(showModal = state) }
    }

    fun addToCart(productModel: ProductModel){
        viewModelScope.launch {
            addToCartUseCase(productModel)
        }
    }
}