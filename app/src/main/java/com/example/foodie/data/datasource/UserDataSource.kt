package com.example.foodie.data.datasource

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource {

    suspend fun isLoginCheck(email: String, username: String, password: String): String =
        withContext(Dispatchers.IO) {
            val status = if ((email == "test@gmail.com" || username == "test") && password == "1234") {
                "login"
            } else {
                "loginFailed"
            }
            return@withContext status
        }

    suspend fun login(account: String, password: String): List<String> =
        withContext(Dispatchers.IO) {
            val userData = if (account == "test@gmail.com" || account == "test") {
                if (password == "1234") {
                    arrayListOf("test@gmail.com", "test", "1234")
                } else {
                    arrayListOf("passwordFailed")
                }
            } else {
                arrayListOf("accountFailed")
            }
            return@withContext userData
        }

    suspend fun signUp(email: String, username: String, password: String): List<String> =
        withContext(Dispatchers.IO) {
            val userData = if (email != "" && username != "" && password != "") {
                arrayListOf(email, username, password)
            } else {
                arrayListOf("")
            }
            return@withContext userData
        }

    suspend fun updateAccount(updateStatus: List<String>) {
        when (updateStatus[0]) {
            "email" -> {
                Log.e("Message", "Update account email")
            }

            "password" -> {
                Log.e("Message", "Update account password")
            }
        }
    }
}