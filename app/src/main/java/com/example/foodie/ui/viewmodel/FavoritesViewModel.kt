package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.repository.FavoriteRepository
import com.example.foodie.data.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel: ViewModel() {
    private val foodRepo = FoodRepository()
    private val favRepo = FavoriteRepository()

    private val _favoriteFoodCardList = MutableLiveData<List<FavoriteFood>>()
    val favoriteFoodCardList: MutableLiveData<List<FavoriteFood>> = _favoriteFoodCardList

    private val _addCartStatus = MutableLiveData<Boolean>()
    val addCartStatus: MutableLiveData<Boolean> = _addCartStatus

    init {
        loadFavoriteFood()
    }

    fun loadFavoriteFood() {
        CoroutineScope(Dispatchers.Main).launch {
            _favoriteFoodCardList.value = favRepo.loadFavoriteFood()
        }
    }

    fun deleteFavoriteFood(favoriteFoodId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            favRepo.deleteFavoriteFood(favoriteFoodId)
            loadFavoriteFood()
        }
    }

    fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username)
            addCartStatus.value = true
        }
    }
}