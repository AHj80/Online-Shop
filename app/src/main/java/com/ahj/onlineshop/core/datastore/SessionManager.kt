package com.ahj.onlineshop.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("online_shop")

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val dataStore = context.dataStore

    companion object {
        val IS_LOGIN = stringPreferencesKey("login")
    }

    val loginUser: Flow<String?> = dataStore.data.map {
        it[IS_LOGIN] ?: ""
    }

    suspend fun saveLogin(status: String?) {
        dataStore.edit {
            it[IS_LOGIN] = status?:""
        }
    }
}