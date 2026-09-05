package com.ahj.onlineshop.feature.cart.presentation.changeAddress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.core.sharedData.address.domain.model.AddressModel
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.DeleteAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.EditAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAddressByIdUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.GetAllAddressUseCase
import com.ahj.onlineshop.core.sharedData.address.domain.useCase.InsertAddressUseCase
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
        sessionManager.defaultAddress.onEach { default ->
            _uiState.update { it.copy(addressDefault = default ?: 0) }
        }.launchIn(viewModelScope)
    }

    fun getAllAddress() {
        _uiState.update { it.copy(status = EditAddressStatus.LOADING, messageStatus = null) }
        getAllAddressUseCase().onEach { result ->
            result
                .onSuccess { address ->
                    if (address.isEmpty()) {
                        _uiState.update {
                            it.copy(
                                status = EditAddressStatus.EMPTY,
                                data = emptyList(),
                                messageStatus = "آدرس ثبت شده ای وجود ندارد"
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                status = EditAddressStatus.SUCCESS,
                                data = address,
                                messageStatus = null
                            )
                        }
                    }

                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = EditAddressStatus.ERROR,
                            messageStatus = error.message
                        )
                    }

                }

        }.launchIn(viewModelScope)
    }

    fun saveAddress() {

        val state = _uiState.value
        viewModelScope.launch {
            val address = AddressModel(
                id = state.currentId,
                receiver = state.stateReceiver,
                postalCode = state.statePostalCode,
                address = state.stateAddress,
                phone = state.statePhone
            )

            if (state.currentId == 0) {
                insertAddressUseCase(address)
                    .onSuccess { newId ->
                        _uiState.update {
                            it.copy(
                                message = "آدرس با موفقیت اضافه گردید",
                                stateReceiver = "",
                                stateAddress = "",
                                statePhone = "",
                                statePostalCode = "",
                                currentId = 0,
                                enabled = true
                            )
                        }

                        savingDefault(newId.toInt())
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
                    _uiState.update { it.copy(messageStatus = error.message) }
                }

        }.launchIn(viewModelScope)

    }

    fun changeModal(status: Boolean) = _uiState.update { it.copy(modal = status) }

    fun deleteAddress(address: AddressModel) {
        viewModelScope.launch {
            deleteAddressUseCase(address)
                .onSuccess {
                    _uiState.update { it.copy(message = "آدرس حذف گردید، لطفا آدرس جدید را انتخاب یا ثبت نمایید") }
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

    fun getAddressDefault(id: Int): Boolean = _uiState.value.addressDefault == id


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

    fun changeEnabled(state: Boolean) = _uiState.update { it.copy(enabled = state) }
    fun resetMessage() = _uiState.update { it.copy(message = null) }

}