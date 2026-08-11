package com.ahj.onlineshop.app.base

import androidx.lifecycle.ViewModel
import com.ahj.onlineshop.core.datastore.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    suspend fun getData(){
        val user = sessionManager.loginUser.first()
    }


}