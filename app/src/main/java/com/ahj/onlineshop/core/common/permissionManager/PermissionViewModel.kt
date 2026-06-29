package com.ahj.onlineshop.core.common.permissionManager

import android.os.Build
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionManager: PermissionManager
) : ViewModel() {

    private val _permissionStatus = MutableStateFlow(PermissionStatus.LOADING)
    val permissionStatus: StateFlow<PermissionStatus> = _permissionStatus.asStateFlow()

    fun checkPermission(pm: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val isGranted = permissionManager.isGranted(pm)
            if (isGranted) _permissionStatus.update { PermissionStatus.GRANTED }
            else
                _permissionStatus.update { PermissionStatus.NEED_REQUEST }
        } else
            _permissionStatus.update { PermissionStatus.GRANTED }

    }
}