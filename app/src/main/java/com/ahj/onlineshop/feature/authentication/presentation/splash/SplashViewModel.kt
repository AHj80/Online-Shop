package com.ahj.onlineshop.feature.authentication.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.common.internetmanager.networkManager
import com.ahj.onlineshop.core.datastore.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()


    fun checking() {
        viewModelScope.launch {
            delay(2000)
            val connectivityManager = context.networkManager()
            val login = sessionManager.loginUser.first()

            _uiState.update {
                it.copy(result = false, statusSplash = StatusSplash.LOADING)
            }
            if (connectivityManager) {
                if (!login.isNullOrEmpty())
                    _uiState.update {
                        it.copy(result = true, statusSplash = StatusSplash.LOGIN_YES)
                    }
                else _uiState.update {
                    it.copy(
                        result = false,
                        statusSplash = StatusSplash.LOGIN_NO
                    )
                }
            } else _uiState.update {
                it.copy(
                    result = false,
                    message = "خطا در برقراری شبکه",
                    statusSplash = StatusSplash.ERROR
                )
            }

        }
    }

    fun tryAgain(){
        _uiState.update {
            it.copy(statusSplash = StatusSplash.LOADING, message = null , result = false)
        }
    }

}