package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.CartFood

class ProductDetailViewModel: ViewModel() {
    val cartFoodObject = MutableLiveData<CartFood>()
    val addCartStatus = MutableLiveData<Boolean>()

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
        cartFoodObject.value = tempCartFoodObject
    }

    fun addProductDetailToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) {
        Log.e("Message", "Add food detail to cart")
        addCartStatus.value = true
    }
}