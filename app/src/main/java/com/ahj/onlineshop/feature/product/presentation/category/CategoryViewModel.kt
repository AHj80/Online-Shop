package com.ahj.onlineshop.feature.product.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.product.domain.usecase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        getCategory()
    }

    fun getCategory() {

        viewModelScope.launch {
            _uiState.update { it.copy(status = CategoryScreenStatus.LOADING , message = null) }
            getCategoryUseCase()
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            status = CategoryScreenStatus.SUCCESS,
                            category = data,
                            message = null
                        )
                    }
                }
                .onFailure { error ->

                    _uiState.update {
                        it.copy(
                            status = CategoryScreenStatus.ERROR ,
                            message = error.message
                        )
                    }
                }
        }
    }


}