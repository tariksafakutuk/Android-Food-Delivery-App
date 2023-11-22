package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountDetailViewModel @Inject constructor(var userRepo: UserRepository) : ViewModel() {
    private val _updateStatus = MutableLiveData<List<String>>()
    val updateStatus: MutableLiveData<List<String>> = _updateStatus

    private val _currentTextError = MutableLiveData<String>()
    val currentTextError: MutableLiveData<String> = _currentTextError

    private val _newTextError = MutableLiveData<String>()
    val newTextError: MutableLiveData<String> = _newTextError

    private val _againNewTextError = MutableLiveData<String>()
    val againNewTextError: MutableLiveData<String> = _againNewTextError

    fun checkUpdate(
        action: String,
        currentText: String,
        newText: String,
        againNewText: String,
        email: String,
        username: String,
        password: String
    ) {
        when (action) {
            "email" -> {
                when (newText) {
                    "" -> {
                        _newTextError.value = "Yeni email adresinizi girmelisiniz"
                    }

                    againNewText -> {
                        _updateStatus.value = arrayListOf("email", newText, email, username, password)
                    }

                    else -> {
                        _againNewTextError.value =
                            "Girilen email bilgisi Yeni Email alanındakiyle aynı olmalıdır"
                    }
                }
            }

            "password" -> {
                if (currentText == "") {
                    _currentTextError.value = "Mevcut şifrenizi girmelisiniz"
                } else if (currentText != password) {
                    _currentTextError.value = "Girilen şifre bilgisi yanlış"
                } else if (newText == "") {
                    _newTextError.value = "Yeni şifrenizi girmelisiniz"
                } else if (newText == againNewText) {
                    _updateStatus.value = arrayListOf("password", newText, email, username, password)
                } else {
                    _againNewTextError.value =
                        "Girilen şifreniz Yeni Şifre alanındakiyle aynı olmalıdır"
                }
            }
        }
    }

    fun updateAccount(updateStatus: List<String>) {
        userRepo.updateAccount(updateStatus)
    }
}