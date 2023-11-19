package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.CartFood

class CartViewModel: ViewModel() {
    val cartFoodCardList = MutableLiveData<List<CartFood>>()
    val totalPrice = MutableLiveData<String>()

    fun loadCartFood(username: String) {
        val cartFoodList = ArrayList<CartFood>()
        val cf1 = CartFood(1, "Ayran", "ayran", 15, 2, "tariksafakutuk")
        val cf2 = CartFood(2, "Baklava", "ayran", 10, 3, "tariksafakutuk")
        cartFoodList.add(cf1)
        cartFoodList.add(cf2)

        cartFoodCardList.value = cartFoodList
    }

    fun calculateTotalPrice() {
        val cf1 = cartFoodCardList.value?.get(0)
        val cf2 = cartFoodCardList.value?.get(1)
        if (cf1 != null && cf2 != null) {
            totalPrice.value = ((cf1.foodPrice * cf1.foodQuantity) + (cf2.foodPrice * cf2.foodQuantity)).toString()
        }
    }

    fun confirmCartTotal() {
        cartFoodCardList.value = arrayListOf()
        totalPrice.value = ""
        Log.e("Message", "Confirm cart total")
    }

    fun deleteCartFood(cartFoodId: Int, username: String) {
        Log.e("Message", "Delete cart food - $username")
        loadCartFood(username)
        calculateTotalPrice()
    }

    fun quantityAction(action: String, cartFoodId: Int, username: String) {
        when (action) {
            "Decrease" -> {
                Log.e("Message", "Decrease quantity")
            }

            "Increase" -> {
                Log.e("Message", "Increase quantity")
            }
        }
        loadCartFood(username)
        calculateTotalPrice()
    }
}