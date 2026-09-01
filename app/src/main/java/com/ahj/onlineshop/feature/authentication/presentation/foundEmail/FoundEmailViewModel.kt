package com.ahj.onlineshop.feature.authentication.presentation.foundEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.authentication.domain.usecase.FoundEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoundEmailViewModel @Inject constructor(
    private val foundEmailUseCase: FoundEmailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FoundEmailUiState())
    val uiState: StateFlow<FoundEmailUiState> = _uiState

    fun foundEmail(email: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(foundEmailStatus = FoundEmailStatus.LOADING, message = null) }
            foundEmailUseCase(email)
                .onSuccess { data ->
                    if (data != null)
                        _uiState.update {
                            it.copy(foundEmailStatus = FoundEmailStatus.SUCCESS, loginModel = data)
                        }
                    else
                        _uiState.update {
                            it.copy(
                                message = "هیچگونه حساب کاربری مرتبط با ایمیل یافت نشد",
                                isError = true
                            )
                        }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            foundEmailStatus = FoundEmailStatus.ERROR,
                            message = error.message,
                            showDialog = true
                        )
                    }
                }

        }
    }

    fun updateEmail(text: String) = _uiState.update { it.copy(stateEmail = text, isError = false) }

    fun checking(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()

    fun onDismissDialog() {
        _uiState.update {
            it.copy(
                showDialog = false,
                foundEmailStatus = FoundEmailStatus.IDLE
            )
        }
    }

    fun resetScreenStatus() {
        _uiState.update { it.copy(foundEmailStatus = FoundEmailStatus.IDLE) }
    }
}