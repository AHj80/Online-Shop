package com.ahj.onlineshop.feature.profile.presentation.notification

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
class NotificationViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    init {
        getAllData()
    }

    fun getAllData() {

        _uiState.update { it.copy(status = NotificationStatus.LOADING, message = null) }
        getHeaderDataUseCase().onEach { result ->
            result
                .onSuccess { data->
                    _uiState.update {
                        it.copy(
                            status = NotificationStatus.EMPTY,
                            message = "هیچ اعلانی برای شما ارسال نشده است!",
                            header = data,
                        )
                    }

                }
                .onFailure { error->
                    _uiState.update {
                        it.copy(
                            status = NotificationStatus.ERROR,
                            message = error.message
                        )
                    }
                }
        }.launchIn(viewModelScope)

    }

}