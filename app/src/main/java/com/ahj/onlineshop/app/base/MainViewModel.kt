package com.ahj.onlineshop.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.common.dispathers.CoroutineDispatchers
import com.ahj.onlineshop.core.database.MyDatabase
import com.ahj.onlineshop.core.datastore.SessionManager
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
class MainViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val sessionManager: SessionManager,
    private val myDatabase: MyDatabase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


    init {
        getData()
    }

    fun getData() {
        getHeaderDataUseCase().onEach { result ->

            result
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            data = data
                        )
                    }

                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }
                }


        }.launchIn(viewModelScope)
    }

    fun logout(){
        viewModelScope.launch(coroutineDispatchers.io) {
            try {
                sessionManager.logout()
                myDatabase.clearAllTables()
            }catch (e: Exception){
                _uiState.update { it.copy(message = e.message) }
            }
        }
    }

}