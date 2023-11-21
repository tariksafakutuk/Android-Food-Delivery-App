package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.repository.FavoriteRepository
import com.example.foodie.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(var foodRepo: FoodRepository, var favRepo: FavoriteRepository): ViewModel() {
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

    fun deleteFavoriteFood(foodId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            favRepo.deleteFavoriteFood(foodId)
            loadFavoriteFood()
        }
    }

    fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username, "Detail")
            _addCartStatus.value = true
        }
    }
}