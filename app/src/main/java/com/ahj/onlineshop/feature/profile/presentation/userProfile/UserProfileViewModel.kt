package com.ahj.onlineshop.feature.profile.presentation.userProfile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.feature.profile.domain.useCase.GetProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfileUiState())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()

    val avatarFlow = sessionManager.setAvatarProfile.onEach { image->
        _uiState.update { it.copy(avatar = image?.toUri()) }
    }.launchIn(viewModelScope)

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            val userId = sessionManager.loginUser.first() ?: ""

            _uiState.update { it.copy(status = UserProfileStatus.LOADING) }
            getProfileDataUseCase(userId)
                .onSuccess { profile ->
                    _uiState.update {
                        it.copy(
                            status = UserProfileStatus.SUCCESS,
                            profile = profile
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = UserProfileStatus.ERROR,
                            message = error.message
                        )
                    }
                }
        }
    }

    fun saveNewAvatar(uri: Uri) {
        viewModelScope.launch {
            sessionManager.saveAvatar(uri.toString())
        }
    }


}