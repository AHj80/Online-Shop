package com.ahj.onlineshop.feature.profile.presentation.userProfile

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.datastore.SessionManager
import com.ahj.onlineshop.feature.profile.domain.useCase.GetProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserProfileUiState())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()

    init {
        observeSessionData()
    }


     fun observeSessionData() {
        viewModelScope.launch {

            sessionManager.setAvatarProfile.collect { avatarString ->
                _uiState.update { it.copy(avatar = avatarString?.toUri()) }
            }
        }

        viewModelScope.launch {
            sessionManager.saveLogin("1")
            sessionManager.loginUser
                .filterNotNull()
                .collectLatest { userId ->
                    if (userId.isNotEmpty()) {
                        _uiState.update { it.copy(userId = userId) }
                        getProfile(userId)
                    }
                }
        }
    }


    fun getProfile(userId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(status = UserProfileStatus.LOADING , message = null) }

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

    // ۳. ذخیره عکس جدید
    fun saveNewAvatar(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val internalUri = copyUriToInternalStorage(context, uri)
            internalUri?.let { savedUri ->
                // ۱. آپدیت سریع UI
                _uiState.update { it.copy(avatar = savedUri) }
                // ۲. ذخیره مسیر جدید و معتبر در DataStore
                sessionManager.saveAvatar(savedUri.toString())
            }
        }
    }

    private fun copyUriToInternalStorage(context: Context, uri: Uri): Uri? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null

            // ۱. پاک کردن فایل‌های قبلی آواتار برای پر نشدن حافظه
            context.filesDir.listFiles()?.forEach { file ->
                if (file.name.startsWith("user_avatar_")) {
                    file.delete()
                }
            }

            // ۲. ساخت فایل جدید با اسم منحصر‌به‌فرد
            val fileName = "user_avatar_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            val outputStream = FileOutputStream(file)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file.toUri()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}