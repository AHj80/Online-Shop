package com.ahj.onlineshop.feature.profile.presentation.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.feature.profile.domain.useCase.GetAddressDataUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.AddNewAddressUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.EditAddressUseCase
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
class AddressViewModel @Inject constructor(
    private val getAddressDataUseCase: GetAddressDataUseCase,
    private val addNewAddressUseCase: AddNewAddressUseCase,
    private val editAddressUseCase: EditAddressUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressUiState())
    val uiState: StateFlow<AddressUiState> = _uiState.asStateFlow()


}