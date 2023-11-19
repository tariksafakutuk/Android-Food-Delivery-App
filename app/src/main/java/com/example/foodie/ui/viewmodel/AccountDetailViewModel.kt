package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountDetailViewModel: ViewModel() {
    private val userRepo = UserRepository()
    val updateStatus = MutableLiveData<List<String>>()
    val currentTextError = MutableLiveData<String>()
    val newTextError = MutableLiveData<String>()
    val againNewTextError = MutableLiveData<String>()

    fun checkUpdate(action: String, currentText: String, newText: String, againNewText: String, password: String) {
        when (action) {
            "email" -> {
                when (newText) {
                    "" -> {
                        newTextError.value = "Yeni email adresinizi girmelisiniz"
                    }

                    againNewText -> {
                        updateStatus.value = arrayListOf("email", newText)
                    }

                    else -> {
                        againNewTextError.value = "Girilen email bilgisi Yeni Email alanındakiyle aynı olmalıdır"
                    }
                }
            }

            "password" -> {
                if (currentText == "") {
                    currentTextError.value = "Mevcut şifrenizi girmelisiniz"
                } else if (currentText != password) {
                    currentTextError.value = "Girilen şifre bilgisi yanlış"
                } else if (newText == "") {
                    newTextError.value = "Yeni şifrenizi girmelisiniz"
                } else if (newText == againNewText) {
                    updateStatus.value = arrayListOf("password", newText)
                } else {
                    againNewTextError.value = "Girilen şifreniz Yeni Şifre alanındakiyle aynı olmalıdır"
                }
            }
        }
    }

    fun updateAccount(updateStatus: List<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            userRepo.updateAccount(updateStatus)
        }
    }
}