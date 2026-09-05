package com.ahj.onlineshop.feature.profile.presentation.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.userProfile.domain.model.UserInformationModel
import com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase.GetUserInformationUseCase
import com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase.SyncUserInformationUseCase
import com.ahj.onlineshop.core.sharedData.userProfile.domain.useCase.UpdateUserInformationUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditUserProfileViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val syncUserInformationUseCase: SyncUserInformationUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val updateUserInformationUseCase: UpdateUserInformationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditUserProfileUiState())
    val uiState: StateFlow<EditUserProfileUiState> = _uiState

    private var initialData = false

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {

            _uiState.update { it.copy(status = EditUserProfileStatus.LOADING, message = null) }
            getHeaderDataUseCase().onEach { result ->

                result
                    .onSuccess { header ->
                        _uiState.update {
                            it.copy(
                                header = header
                                )
                        }
                        if (!initialData) {
                            syncUserInformationUseCase(header.userId)
                                .onSuccess {
                                    getDataFlow(header.userId)
                                }
                                .onFailure { error ->

                                    _uiState.update {
                                        it.copy(
                                            message = error.message,
                                            status = EditUserProfileStatus.ERROR,
                                            alertDialog = true
                                        )
                                    }
                                }
                        }
                    }
                    .onFailure { error ->
                        _uiState.update {
                            it.copy(
                                status = EditUserProfileStatus.ERROR,
                                message = error.message,
                                alertDialog = true
                            )
                        }
                    }

            }.launchIn(viewModelScope)

        }

    }


    fun getDataFlow(id: String) {

        getUserInformationUseCase(id).onEach { result ->
            result
                .onSuccess { information ->
                    _uiState.update {
                        val updatesField = !initialData
                        it.copy(
                            status = EditUserProfileStatus.SUCCESS,
                            profile = information,
                            statePhone = if (updatesField) editAutoText(
                                information?.phone ?: ""
                            ) else it.statePhone,
                            stateEmail = information?.email ?: "",
                            stateName = if (updatesField) editAutoText(
                                information?.name ?: ""
                            ) else it.stateName,
                            gender = if (updatesField) information?.gender ?: false else it.gender
                        )
                    }
                    initialData = true
                }
                .onFailure { error ->

                    _uiState.update {
                        it.copy(
                            status = EditUserProfileStatus.ERROR,
                            message = error.message,
                            alertDialog = true
                        )
                    }
                }
        }.launchIn(viewModelScope)
    }


    fun updateInformation(
        name: String,
        phone: String,
        gender: Boolean,
        email: String
    ) {

        val information = UserInformationModel(
            name = name,
            phone = phone,
            email = email,
            gender = gender
        )
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, message = null) }
            updateUserInformationUseCase(_uiState.value.header?.userId ?: "", information)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            message = "تغییرات با موفقیت ذخیره گردید",
                            isLoading = false,
                            alertDialog = true
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            message = " خطا در بروزرسانی اطلاعات: ${error.message}",
                            isLoading = false,
                            alertDialog = true
                        )
                    }
                }

        }
    }


    fun changeName(name: String) = _uiState.update { it.copy(stateName = name) }

    fun changePhone(phone: String) {

        if (phone.length <= 11 || phone.isBlank())
            _uiState.update { it.copy(statePhone = phone) }
    }

    fun closeAlertDialog() = _uiState.update { it.copy(alertDialog = false, message = null) }

    fun editAutoText(text: String): String {
        val lowercaseText = text.lowercase().trim()
        return if (lowercaseText.contains("name") || lowercaseText.contains("phone")
        )
            ""
        else text
    }

    fun changeGender(state: Boolean) = _uiState.update { it.copy(gender = state) }
}

