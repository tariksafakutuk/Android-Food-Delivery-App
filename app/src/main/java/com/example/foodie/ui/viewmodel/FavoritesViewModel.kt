package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.FavoriteFood

class FavoritesViewModel: ViewModel() {
    val favoriteFoodCardList = MutableLiveData<List<FavoriteFood>>()
    val addCartStatus = MutableLiveData<Boolean>()

    init {
        loadFavoriteFood()
    }

    fun loadFavoriteFood() {
        val favoriteFoodList = ArrayList<FavoriteFood>()
        val f1 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        val f2 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        favoriteFoodList.add(f1)
        favoriteFoodList.add(f2)

        favoriteFoodCardList.value = favoriteFoodList
    }

    fun deleteFavoriteFood(favoriteFoodId: Int) {
        Log.e("Message", "Delete favorite food")
        loadFavoriteFood()
    }

    fun addFavoritesFoodToCart(favoriteFood: FavoriteFood, username: String) {
        Log.e("Message", "Add favorite food to cart")
        addCartStatus.value = true
    }
}