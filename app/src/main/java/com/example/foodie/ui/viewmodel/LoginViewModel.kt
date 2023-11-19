package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(): ViewModel() {
    val loginStatus = MutableLiveData<String>()
    val userData = MutableLiveData<List<String>>()

    init {
        loginStatus.value = "loading"
    }

    fun isLoginCheck(email: String, username: String, password: String) {
        if ((email == "test@gmail.com" || username == "test") && password == "1234") {
            loginStatus.value = "login"
        } else {
            loginStatus.value = "loginFailed"
        }
    }

    fun login(account: String, password: String) {
        if (account == "test@gmail.com" || account == "test") {
            if (password == "1234") {
                userData.value = arrayListOf("test@gmail.com", "test", "1234")
                loginStatus.value = "login"
            } else {
                userData.value = arrayListOf("passwordFailed")
            }
        } else {
            userData.value = arrayListOf("accountFailed")
        }
    }
}