package com.example.foodie.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.foodie.utils.dataStore
import kotlinx.coroutines.flow.first

class LoginPref(var context: Context) {
    companion object {
        val EMAIL_KEY = stringPreferencesKey("EMAIL")
        val USERNAME_KEY = stringPreferencesKey("USERNAME")
        val PASSWORD_KEY = stringPreferencesKey("PASSWORD")
    }

    suspend fun createEmail(email: String) {
        context.dataStore.edit {
            it[EMAIL_KEY] = email
        }
    }

    suspend fun readEmail(): String {
        val p = context.dataStore.data.first()
        return p[EMAIL_KEY] ?: ""
    }

    suspend fun deleteEmail() {
        context.dataStore.edit {
            it.remove(EMAIL_KEY)
        }
    }

    suspend fun createUsername(username: String) {
        context.dataStore.edit {
            it[USERNAME_KEY] = username
        }
    }

    suspend fun readUsername(): String {
        val p = context.dataStore.data.first()
        return p[USERNAME_KEY] ?: ""
    }

    suspend fun deleteUsername() {
        context.dataStore.edit {
            it.remove(USERNAME_KEY)
        }
    }

    suspend fun createPassword(password: String) {
        context.dataStore.edit {
            it[PASSWORD_KEY] = password
        }
    }

    suspend fun readPassword(): String {
        val p = context.dataStore.data.first()
        return p[PASSWORD_KEY] ?: ""
    }

    suspend fun deletePassword() {
        context.dataStore.edit {
            it.remove(PASSWORD_KEY)
        }
    }
}