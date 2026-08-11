package com.ahj.onlineshop.feature.cart.presentation.changeAddress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.common.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.common.sharedData.address.domain.useCase.DeleteAddressUseCase
import com.ahj.onlineshop.core.common.sharedData.address.domain.useCase.EditAddressUseCase
import com.ahj.onlineshop.core.common.sharedData.address.domain.useCase.GetAddressByIdUseCase
import com.ahj.onlineshop.core.common.sharedData.address.domain.useCase.GetAllAddressUseCase
import com.ahj.onlineshop.core.common.sharedData.address.domain.useCase.InsertAddressUseCase
import com.ahj.onlineshop.core.datastore.SessionManager
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
class EditAddressViewModel @Inject constructor(
    private val getAllAddressUseCase: GetAllAddressUseCase,
    private val editAddressUseCase: EditAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val getAddressByIdUseCase: GetAddressByIdUseCase,
    private val insertAddressUseCase: InsertAddressUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditAddressUiState())
    val uiState: StateFlow<EditAddressUiState> = _uiState.asStateFlow()


    init {
        getAllAddress()
    }

    fun getAllAddress() {
        _uiState.update { it.copy(status = EditAddressStatus.LOADING, message = null) }
        getAllAddressUseCase().onEach { result ->
            result
                .onSuccess { address ->
                    _uiState.update {
                        it.copy(
                            status = EditAddressStatus.SUCCESS,
                            message = null,
                            data = address
                        )
                    }

                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = EditAddressStatus.ERROR,
                            message = error.message
                        )
                    }

                }

        }.launchIn(viewModelScope)
    }

    fun saveAddress(
        id: Int = 0,
        receiver: String,
        fullAddress: String,
        phone: String,
        postalCode: String
    ) {

        viewModelScope.launch {
            val address = AddressModel(
                id = id,
                receiver = receiver,
                postalCode = postalCode,
                address = fullAddress,
                phone = phone
            )

            if (id == 0) {
                insertAddressUseCase(address)
                    .onSuccess {
                        _uiState.update {
                            it.copy(
                                message = "آدرس با موفقیت اضافه گردید",
                                stateReceiver = "",
                                stateAddress = "",
                                statePhone = "",
                                statePostalCode = "",
                                currentId = 0
                            )
                        }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(message = error.message) }
                    }
            } else {
                editAddressUseCase(address)
                    .onSuccess {
                        _uiState.update {
                            it.copy(
                                message = "تغییرات با موفقیت ذخیره گردید"
                            )
                        }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(message = error.message) }
                    }
            }
        }
    }


    fun getAddressById(id: Int) {

        getAddressByIdUseCase(id).onEach { result ->

            result
                .onSuccess { address ->
                    _uiState.update {
                        it.copy(
                            currentId = address.id,
                            address = address,
                            stateReceiver = address.receiver,
                            stateAddress = address.address,
                            statePhone = address.phone,
                            statePostalCode = address.postalCode
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }
                }

        }.launchIn(viewModelScope)

    }

    fun changeModal(status: Boolean) = _uiState.update { it.copy(modal = status) }

    fun deleteAddress(address: AddressModel) {
        viewModelScope.launch {
            deleteAddressUseCase(address)
                .onSuccess {
                    _uiState.update { it.copy(message = "آدرس حذف گردید") }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(message = error.message)
                    }
                }
        }
    }

    fun savingDefault(id: Int) {
        viewModelScope.launch {
            sessionManager.saveDefaultAddress(id)
        }
    }

    fun getAddressDefault(id: Int): Boolean {
        viewModelScope.launch {
            sessionManager.defaultAddress.collect { address ->
                address?.let { default ->
                    _uiState.update { it.copy(addressDefault = default) }
                }
            }
        }
        return _uiState.value.addressDefault == id
    }

    fun resetInput() = _uiState.update {
        it.copy(
            stateReceiver = "",
            stateAddress = "",
            statePhone = "",
            statePostalCode = "",
            currentId = 0
        )

    }

    fun updateTextReceiver(text: String) = _uiState.update { it.copy(stateReceiver = text) }
    fun updateTextAddress(text: String) = _uiState.update { it.copy(stateAddress = text) }
    fun updateTextPhone(text: String) {

        if (text.length <= 11 || text.isBlank())
            _uiState.update { it.copy(statePhone = text) }
    }

    fun updateTextPostalCode(text: String) {
        if (text.isBlank() || text.length <= 10)
            _uiState.update { it.copy(statePostalCode = text) }
    }

    fun checkingEnabled(): Boolean {
        val receiver = _uiState.value.stateReceiver.isNotBlank()
        val address = _uiState.value.stateAddress.isNotBlank()
        val phone = _uiState.value.statePhone.isNotBlank() && _uiState.value.statePhone.length == 11
        val postalCode =
            _uiState.value.statePostalCode.isNotBlank() && _uiState.value.statePostalCode.length == 10
        return receiver && address && phone && postalCode
    }


}