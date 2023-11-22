package com.example.foodie.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.foodie.data.datasource.UserDataSource
import com.example.foodie.data.entity.AccountCardItem

class UserRepository(var uds: UserDataSource) {
    fun isLoginCheck(): MutableLiveData<List<String>> =
        uds.isLoginCheck()

    fun login(account: String, password: String): MutableLiveData<List<String>> =
        uds.login(account, password)

    fun signUp(email: String, username: String, password: String): List<String> =
        uds.signUp(email, username, password)

    suspend fun loadAccountCard(): List<AccountCardItem> =
        uds.loadAccountCard()

    fun updateAccount(updateStatus: List<String>) =
        uds.updateAccount(updateStatus)
}