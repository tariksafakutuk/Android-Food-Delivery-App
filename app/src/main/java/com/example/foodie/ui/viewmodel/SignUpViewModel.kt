package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    val userData = MutableLiveData<List<String>>()

    fun signUp(email: String, username: String, password: String) {
        if (email != "" && username != "" && password != "") {
            userData.value = arrayListOf(email, username, password)
        } else {
            userData.value = arrayListOf("")
        }
    }
}