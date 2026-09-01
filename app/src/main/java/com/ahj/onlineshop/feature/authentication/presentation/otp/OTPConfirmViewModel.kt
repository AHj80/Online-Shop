package com.ahj.onlineshop.feature.authentication.presentation.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.app.base.MyApp
import com.ahj.onlineshop.core.common.dispathers.CoroutineDispatchers
import com.ahj.onlineshop.core.common.notification.NotificationImpl
import com.ahj.onlineshop.feature.authentication.domain.usecase.SendOtpUseCase
import com.ahj.onlineshop.feature.authentication.domain.usecase.VerifyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OTPConfirmViewModel @Inject constructor(
    private val sendOtpUseCase: SendOtpUseCase,
    private val verifyUseCase: VerifyUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val notificationImpl: NotificationImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(OTPConfirmUiState())
    val uiState: StateFlow<OTPConfirmUiState> = _uiState.asStateFlow()


    private var timerJob: Job? = null

    init {
        sendOtpCode()
    }

    private var generateCode: String? = null


    fun sendOtpCode() {

        viewModelScope.launch(coroutineDispatchers.main) {
            _uiState.update {
                it.copy(
                    status = OTPConfirmStatus.LOADING,
                    message = null,
                )
            }
            sendOtpUseCase()
                .onSuccess { code ->
                    generateCode = code

                    notificationImpl.createNotification(
                        1,
                        "کد احراز هویت",
                        "کد احراز هویت شما : $code میباشد ",
                        MyApp.AUTH_OTP
                    )

                    otpTimer()
                    _uiState.update {
                        it.copy(
                            status = OTPConfirmStatus.IDLE,
                            message = null,
                        )
                    }
                }
                .onFailure { msg ->
                    _uiState.update {
                        it.copy(
                            status = OTPConfirmStatus.ERROR,
                            message = msg.message
                        )
                    }
                }

        }

    }

    fun otpChecking(input: String) {
        _uiState.update { it.copy(status = OTPConfirmStatus.LOADING) }
        verifyUseCase(input, generateCode)
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        status = OTPConfirmStatus.SUCCESS,
                        message = null,
                        result = result
                    )
                }
            }
            .onFailure { msg ->
                _uiState.update {
                    it.copy(
                        status = OTPConfirmStatus.ERROR,
                        message = msg.message,
                        result = false,
                        showAlertDialog = true
                    )
                }
            }

    }

    fun otpTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch(coroutineDispatchers.main) {
            _uiState.update { it.copy(reSendCode = false) }
            for (i in 59 downTo 0) {
                delay(1000)
                _uiState.update { it.copy(timer = i) }
            }
            _uiState.update { it.copy(reSendCode = true) }
            generateCode = null
        }

    }


    fun stateOTPChanged(text: String) =
        _uiState.update {
            it.copy(
                status = OTPConfirmStatus.IDLE,
                message = null,
                stateOTP = text.take(4).filter { input -> input.isDigit() })
        }

    fun enabledChange(text: String): Boolean =
        text.isNotBlank() && text.length == 4

    fun onDismiss(){
        _uiState.update { it.copy(showAlertDialog = false , status = OTPConfirmStatus.IDLE) }
    }


}