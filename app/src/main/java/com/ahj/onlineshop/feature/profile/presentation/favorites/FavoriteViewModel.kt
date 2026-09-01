package com.ahj.onlineshop.feature.profile.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import com.ahj.onlineshop.core.sharedData.favorite.domain.useCase.DeleteFavoriteUseCase
import com.ahj.onlineshop.core.sharedData.favorite.domain.useCase.GetAllDataFavoriteUseCase
import com.ahj.onlineshop.feature.profile.domain.model.FavoriteDataModel
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllDataFavoriteUseCase: GetAllDataFavoriteUseCase,
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }


   private fun combinedData() =
        combine(
            getHeaderDataUseCase(),
            getAllDataFavoriteUseCase()
        ) { headerRes, favoriteRes ->
            if (headerRes.isSuccess && favoriteRes.isSuccess) {
                Result.success(
                    FavoriteDataModel(
                        headerData = headerRes.getOrThrow(),
                        favoriteData = favoriteRes.getOrThrow()
                    )
                )
            } else {
                val error = headerRes.exceptionOrNull() ?: favoriteRes.exceptionOrNull()
                ?: Exception("خطای داخلی")
                Result.failure(error)
            }

        }

    fun loadData() {
        _uiState.update { it.copy(status = FavoriteStatus.LOADING, message = null) }
        combinedData().onEach { result ->
            result
                .onSuccess { data ->
                    if (data.favoriteData.isEmpty()) {
                        _uiState.update {
                            it.copy(
                                status = FavoriteStatus.EMPTY,
                                profile = data.headerData.profile,
                                data = emptyList(),
                                message = "لیست علاقه مندی ها خالی میباشد",
                                avatar = data.headerData.avatar
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                status = FavoriteStatus.SUCCESS,
                                profile = data.headerData.profile,
                                data = data.favoriteData,
                                message = null,
                                avatar = data.headerData.avatar
                            )
                        }
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            message = error.message,
                            status = FavoriteStatus.ERROR
                        )
                    }
                }

        }.launchIn(viewModelScope)
    }

    fun deletedFavorite(favorite: FavoriteModel) {
        viewModelScope.launch {
            deleteFavoriteUseCase(favorite)
        }
    }


}