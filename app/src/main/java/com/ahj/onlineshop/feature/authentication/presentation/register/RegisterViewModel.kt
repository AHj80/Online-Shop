package com.ahj.onlineshop.feature.authentication.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel
import com.ahj.onlineshop.feature.authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun sendRegisterData(email: String, pass: String) {

        val registerModel = RegisterModel(email = email, password = pass )
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    registerStatus = RegisterStatus.LOADING,
                    message = null
                )
            }
            registerUseCase(registerModel)
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            registerStatus = RegisterStatus.SUCCESS,
                            registerModel = data,
                            message = "ثبت نام با موفقیت انجام شد"
                        )
                    }
                }
                .onFailure { message ->
                    _uiState.update {
                        it.copy(
                            registerStatus = RegisterStatus.ERROR,
                            showDialog = true,
                            message = "عملیات همراه با خطا بود :${message.message.toString()}"
                        )
                    }
                }
        }

    }

    fun updateEmail(text: String) = _uiState.update { it.copy(stateEmail = text ) }
    fun updatePass(text: String) = _uiState.update { it.copy(statePass = text) }
    fun updatePassConfirm(text: String) = _uiState.update { it.copy(statePassConfirm = text) }

    fun validating(email: String, pass: String, passConfirm: String): Boolean {

        val email = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()
        val passMatch = pass == passConfirm && pass.isNotBlank() && passConfirm.isNotBlank() && pass.length >= 8
        return email && passMatch
    }


    fun onDismissAlertDialog() {
        _uiState.update {
            it.copy(
                showDialog = false,
                registerStatus = RegisterStatus.IDLE
            )
        }
    }

}