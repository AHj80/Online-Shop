package com.ahj.onlineshop.feature.product.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()


    init {
        getHomeData()
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


    fun updateText(text: String) =
        _uiState.update { it.copy(stateText = text) }

}