package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.CartFood
import com.example.foodie.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodRepo: FoodRepository): ViewModel() {
    private val _cartFoodCardList = MutableLiveData<List<CartFood>>()
    val cartFoodCardList: MutableLiveData<List<CartFood>> = _cartFoodCardList

    private val _totalPrice = MutableLiveData<String>()
    val totalPrice: MutableLiveData<String> = _totalPrice

    fun loadCartFood(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _cartFoodCardList.value = foodRepo.loadCartFood(username)
            calculateTotalPrice()
        }
    }

    private fun calculateTotalPrice() {
        val cartFoodList = _cartFoodCardList.value as List<CartFood>
        CoroutineScope(Dispatchers.Main).launch {
            _totalPrice.value = foodRepo.calculateTotalPrice(cartFoodList)
        }
    }

    fun confirmCartTotal(username: String) {
        val cartFoodList = _cartFoodCardList.value as List<CartFood>
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.confirmCartTotal(cartFoodList, username)
            _cartFoodCardList.value = arrayListOf()
            _totalPrice.value = ""
        }
    }

    fun cartFoodAction(
        action: String,
        cartFoodId: Int,
        foodName: String,
        foodImageName: String,
        foodPrice: Int,
        foodQuantity: Int,
        username: String
    ) {
        when (action) {
            "Delete" -> {
                CoroutineScope(Dispatchers.Main).launch {
                    foodRepo.deleteCartFood(cartFoodId, username)
                    loadCartFood(username)
                }
            }

            else -> {
                CoroutineScope(Dispatchers.Main).launch {
                    foodRepo.deleteCartFood(cartFoodId, username)
                    foodRepo.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username, "Increase-Decrease")
                    loadCartFood(username)
                }
            }
        }
    }
}