package com.ahj.onlineshop.feature.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun loginUser(email: String, pass: String) {

        val loginModel = LoginModel(email = email, password = pass)

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loginStatus = LoginStatus.LOADING,
                    isError = false,
                    message = null,
                )
            }
            loginUseCase(loginModel)
                .onSuccess { statusChecking ->
                    _uiState.update {
                        it.copy(
                            loginModel = statusChecking,
                            loginStatus = LoginStatus.SUCCESS,
                            isError = false,
                            message = "ورود با موفقیت انجام شد"
                        )
                    }
                    sessionManager.saveLogin(statusChecking?.id)
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            showAlertDialog = true,
                            message = "خطا:${error.message.toString()}",
                            isError = true,
                            loginStatus = LoginStatus.ERROR
                        )
                    }
                }
        }

    }

    fun changeEmail(text: String) =
        _uiState.update {
            it.copy(
                stateEmail = text,
                isError = false,
            )
        }

    fun changePass(text: String) =
        _uiState.update {
            it.copy(
                statePass = text,
            )
        }

    fun enabledChange(email: String, pass: String): Boolean {
        val email = email.length > 9 && email.isNotBlank() && email.contains("@gmail.com")
        val pass = pass.length >= 8 && pass.isNotBlank()
        return email && pass
    }

    fun onDismissDialog() {
        _uiState.update {
            it.copy(
                showAlertDialog = false,
                message = null
            )
        }
    }

    fun onDismissAlertDialog() {
        _uiState.update {
            it.copy(
                showAlertDialog = false,
            )
        }
    }


}