package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.entity.AccountCardItem
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

    suspend fun loadAccountCard(): List<AccountCardItem> =
        withContext(Dispatchers.IO) {
            val accountCardItemList = ArrayList<AccountCardItem>()
            val a1 = AccountCardItem("vc_email", "Email Değişikliği")
            val a2 = AccountCardItem("vc_password", "Şifre Değişikliği")
            val a3 = AccountCardItem("vc_logout", "Çıkış")
            accountCardItemList.add(a1)
            accountCardItemList.add(a2)
            accountCardItemList.add(a3)

            return@withContext accountCardItemList
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