package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var userRepo: UserRepository) : ViewModel() {
    var userData = MutableLiveData<List<String>>()

    init {
        isLoginCheck()
    }

    fun isLoginCheck() {
        userData = userRepo.isLoginCheck()
    }

    fun login(account: String, password: String) {
        userData = userRepo.login(account, password)
    }
}