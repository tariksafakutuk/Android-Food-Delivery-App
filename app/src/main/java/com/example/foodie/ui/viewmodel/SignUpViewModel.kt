package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private val userRepo = UserRepository()
    val userData = MutableLiveData<List<String>>()

    fun signUp(email: String, username: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            userData.value = userRepo.signUp(email, username, password)
        }
    }
}