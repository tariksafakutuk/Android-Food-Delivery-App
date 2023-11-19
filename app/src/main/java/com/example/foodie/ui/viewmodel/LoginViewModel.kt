package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {
    private val userRepo = UserRepository()
    val loginStatus = MutableLiveData<String>()
    val userData = MutableLiveData<List<String>>()

    init {
        loginStatus.value = "loading"
    }

    fun isLoginCheck(email: String, username: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            loginStatus.value = userRepo.isLoginCheck(email, username, password)
        }
    }

    fun login(account: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            userData.value = userRepo.login(account, password)
        }
    }
}