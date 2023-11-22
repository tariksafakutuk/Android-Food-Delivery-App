package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(var userRepo: UserRepository) : ViewModel() {
    var userData = MutableLiveData<List<String>>()

    fun signUp(email: String, username: String, password: String) {
        userData.value = userRepo.signUp(email, username, password)
    }
}