package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.AccountCardItem

class AccountViewModel: ViewModel() {
    val accountCardList = MutableLiveData<List<AccountCardItem>>()

    init {
        loadAccountCard()
    }

    fun loadAccountCard() {
        val accountCardItemList = ArrayList<AccountCardItem>()
        val a1 = AccountCardItem("vc_email", "Email Değişikliği")
        val a2 = AccountCardItem("vc_password", "Şifre Değişikliği")
        val a3 = AccountCardItem("vc_logout", "Çıkış")
        accountCardItemList.add(a1)
        accountCardItemList.add(a2)
        accountCardItemList.add(a3)

        accountCardList.value = accountCardItemList
    }
}