package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.AccountCardItem
import com.example.foodie.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(var userRepo: UserRepository): ViewModel() {
    private val _accountCardList = MutableLiveData<List<AccountCardItem>>()
    val accountCardList: MutableLiveData<List<AccountCardItem>> = _accountCardList

    init {
        loadAccountCard()
    }

    fun loadAccountCard() {
        CoroutineScope(Dispatchers.Main).launch {
            _accountCardList.value = userRepo.loadAccountCard()
        }
    }
}