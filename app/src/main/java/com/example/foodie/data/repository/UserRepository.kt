package com.example.foodie.data.repository

import com.example.foodie.data.datasource.UserDataSource

class UserRepository {
    private val uds = UserDataSource()

    suspend fun isLoginCheck(email: String, username: String, password: String): String =
        uds.isLoginCheck(email, username, password)

    suspend fun login(account: String, password: String): List<String> =
        uds.login(account, password)

    suspend fun signUp(email: String, username: String, password: String): List<String> =
        uds.signUp(email, username, password)

    suspend fun updateAccount(updateStatus: List<String>) =
        uds.updateAccount(updateStatus)
}