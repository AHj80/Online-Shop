package com.ahj.onlineshop.feature.authentication.presentation.resetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.authentication.domain.usecase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState: StateFlow<ResetPasswordUiState> = _uiState.asStateFlow()

    fun resetPass(id: String, pass: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(status = ResetPasswordStatus.LOADING, message = null) }
            resetPasswordUseCase(id, pass)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            status = ResetPasswordStatus.SUCCESS,
                            message = "رمزعبور با موفقیت تغییر یافت"
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = ResetPasswordStatus.ERROR,
                            message = error.message,
                            showAlertDialog = true
                        )
                    }
                }

        }
    }

    fun validating(pass: String, passConfirm: String): Boolean {
        val length = pass.length >= 8
        val notBlank = pass.isNotBlank()
        val isDigit = pass.any { it.isDigit() }
        val upperCase = pass.any { it.isUpperCase() }
        val isMatch = pass == passConfirm

        return length && notBlank && isDigit && upperCase && isMatch
    }


    fun statePassChange(text: String) =
        _uiState.update {
            it.copy(
                statePass = text,
                isError = false,
                message = null
            )
        }

    fun statePassConfirmChange(text: String) =
        _uiState.update {
            it.copy(
                statePassConfirm = text,
                isError = false,
                message = null
            )
        }

    fun onDismiss() = _uiState.update { it.copy(showAlertDialog = false , status = ResetPasswordStatus.IDELE) }

}