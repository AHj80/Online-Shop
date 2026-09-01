package com.ahj.onlineshop.feature.profile.presentation.changePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase.ChangePasswordUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val headerDataUseCase: GetHeaderDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChangePasswordUiState())
    val uiState: StateFlow<ChangePasswordUiState> = _uiState.asStateFlow()

    init {
        getHeaderData()
    }

    fun getHeaderData() {
        _uiState.update { it.copy(status = ChangePasswordStatus.LOADING, message = null) }
        headerDataUseCase().onEach { result ->
            result
                .onSuccess { header ->
                    _uiState.update {
                        it.copy(
                            status = ChangePasswordStatus.SUCCESS,
                            message = null,
                            header = header
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = ChangePasswordStatus.ERROR,
                            message = error.message,

                        )
                    }
                }
        }.launchIn(viewModelScope)

    }

    fun changingPassword(id: String, newPass: String, oldPass: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(message = "در حال بررسی", loading = true) }
            changePasswordUseCase(
                id,
                oldPass,
                newPass
            )
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            message = "رمز عبور با موفقیت تغییر یافت",
                            alertDialog = true,
                            oldPass = "",
                            newPass = "",
                            confirmPass = ""
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            message = error.message,
                            loading = false,
                            alertDialog = true,
                            isError = true
                        )
                    }
                }
        }
    }


    fun oldPassChange(text: String) {

        val filterSpace = text.replace(" " , "")
            _uiState.update {
                it.copy(
                    oldPass = filterSpace,
                    isError = false,
                    message = null
                )
            }
    }

    fun newPassChange(text: String) {
        val filterSpace = text.replace(" " , "")
        _uiState.update {
            it.copy(
                newPass = filterSpace,
                isError = false,
                message = null
            )
        }
    }

    fun confirmPassChange(text: String) {
        val filterSpace = text.replace(" " , "")
        _uiState.update {
            it.copy(
                confirmPass = filterSpace,
                isError = false,
                message = null
            )
        }
    }

    fun changingVisibleEye() {
        _uiState.update { it.copy(visibleEye = !it.visibleEye) }
    }

    fun validating(oldPass: String, newPass: String, passConfirm: String): Boolean {
        val length = newPass.length >= 8 && oldPass.length >= 8 && passConfirm.length >= 8
        val notBlank = newPass.isNotBlank() && passConfirm.isNotBlank() && oldPass.isNotBlank()
        val isDigit = newPass.any { it.isDigit() }
        val upperCase = newPass.any { it.isUpperCase() }
        val isMatch = newPass == passConfirm

        return length && notBlank && isDigit && upperCase && isMatch
    }

    fun changeAlertDialog(state: Boolean) = _uiState.update { it.copy(alertDialog = state) }

}