package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var userRepo: UserRepository): ViewModel() {
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: MutableLiveData<String> = _loginStatus

    private val _userData = MutableLiveData<List<String>>()
    val userData: MutableLiveData<List<String>> = _userData

    init {
        _loginStatus.value = "loading"
    }

    fun isLoginCheck(email: String, username: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _loginStatus.value = userRepo.isLoginCheck(email, username, password)
        }
    }

    fun login(account: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _userData.value = userRepo.login(account, password)
        }
    }
}