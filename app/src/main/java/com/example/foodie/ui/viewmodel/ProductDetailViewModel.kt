package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.CartFood
import com.example.foodie.data.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailViewModel: ViewModel() {
    private val foodRepo = FoodRepository()

    private val _cartFoodObject = MutableLiveData<CartFood>()
    val cartFoodObject: MutableLiveData<CartFood> = _cartFoodObject

    private val _addCartStatus = MutableLiveData<Boolean>()
    val addCartStatus: MutableLiveData<Boolean> = _addCartStatus

    fun setFavorite(foodId: Int, foodName: String, foodImageName: String, foodPrice: Int) {
        Log.e("Message", "Set favorite")
    }

    fun removeFavorite(foodId: Int) {
        Log.e("Message", "Remove favorite")
    }

    fun foodQuantityChange(action: String, tempCartFoodObject: CartFood, foodPrice: Int) {
        when (action) {
            "Decrease" -> {
                if (tempCartFoodObject.foodQuantity != 1) {
                    tempCartFoodObject.foodQuantity -= 1
                }
            }

            "Increase" -> {
                tempCartFoodObject.foodQuantity += 1
            }
        }
        tempCartFoodObject.foodPrice = tempCartFoodObject.foodQuantity * foodPrice
        _cartFoodObject.value = tempCartFoodObject
    }

    fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username)
            _addCartStatus.value = true
        }
    }
}