package com.ahj.onlineshop.feature.profile.presentation.userExperience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.useCase.GetUserExperienceUseCase
import com.ahj.onlineshop.feature.profile.domain.model.ExperienceDataModel
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class UserExperienceViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val getUserExperienceUseCase: GetUserExperienceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserExperienceUiState())
    val uiState: StateFlow<UserExperienceUiState> = _uiState.asStateFlow()

    init {
        getAllData()
    }

    private fun combinedData() =
        combine(
            getHeaderDataUseCase(),
            getUserExperienceUseCase()
        ) { header, experience ->
            if (header.isSuccess && experience.isSuccess) {
                Result.success(
                    ExperienceDataModel(
                        header.getOrThrow(),
                        experience.getOrThrow()
                    )
                )
            } else {
                val error = header.exceptionOrNull() ?: experience.exceptionOrNull()
                ?: Exception("خطای ناشناخته")
                Result.failure(
                    error
                )
            }

        }


    fun getAllData() {

        _uiState.update {
            it.copy(status = UserExperienceStatus.LOADING, message = null)
        }
        combinedData().onEach { result ->
            result
                .onSuccess { data ->
                    if (data.experienceData.isNotEmpty()) {
                        _uiState.update {
                            it.copy(
                                status = UserExperienceStatus.SUCCESS,
                                header = data.header,
                                experienceData = data.experienceData,
                                message = null
                            )
                        }

                    } else {
                        _uiState.update {
                            it.copy(
                                status = UserExperienceStatus.EMPTY,
                                header = data.header,
                                experienceData = emptyList(),
                                message = "هیچ تجربه ای ثبت نشده است"
                            )
                        }

                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = UserExperienceStatus.ERROR,
                            message = error.message
                        )
                    }

                }
        }.launchIn(viewModelScope)

    }
}