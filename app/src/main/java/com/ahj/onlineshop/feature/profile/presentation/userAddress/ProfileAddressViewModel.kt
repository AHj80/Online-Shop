package com.ahj.onlineshop.feature.profile.presentation.userAddress

import androidx.lifecycle.ViewModel
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.DeleteAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.EditAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAddressByIdUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAllAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.InsertAddressUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.GetAddressDataUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.GetHeaderDataUseCase
import com.ahj.onlineshop.feature.profile.domain.useCase.GetProfileDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ProfileAddressViewModel @Inject constructor(
    private val getHeaderDataUseCase: GetHeaderDataUseCase,
    private val getAllAddressUseCase: GetAllAddressUseCase,
    private val editAddressUseCase: EditAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val getAddressByIdUseCase: GetAddressByIdUseCase,
    private val insertAddressUseCase: InsertAddressUseCase,
    private val sessionManager: SessionManager

) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileAddressUiState())
    val uiState: StateFlow<ProfileAddressUiState> = _uiState.asStateFlow()

    fun combinedData() =
        combine(
            getHeaderDataUseCase(),
            getAllAddressUseCase()
        ) { header , address->


        }

    fun getAddressData() {

    }
}