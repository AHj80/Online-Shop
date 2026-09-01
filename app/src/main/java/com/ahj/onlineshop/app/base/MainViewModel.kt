package com.ahj.onlineshop.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


    init {
        getData()
    }

    fun getData() {
        getHeaderDataUseCase().onEach { result ->

            result
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            data = data
                        )
                    }

                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }
                }


        }.launchIn(viewModelScope)
    }


}