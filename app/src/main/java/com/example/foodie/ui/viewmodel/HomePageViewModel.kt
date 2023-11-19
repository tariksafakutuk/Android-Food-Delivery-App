package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food

class HomePageViewModel: ViewModel() {
    val foodCardList = MutableLiveData<HashMap<String, List<Any>>>()

    init {
        loadFood()
    }

    fun loadFood() {
        val foodList = ArrayList<Food>()
        val f1 = Food(1, "Ayran", "ayran", 15)
        val f2 = Food(2, "Baklava", "ayran", 15)
        foodList.add(f1)
        foodList.add(f2)

        val favoriteFoodList = ArrayList<FavoriteFood>()
        val ff1 = FavoriteFood(1, 2, "Baklava", "ayran", 15)
        favoriteFoodList.add(ff1)

        val tempHashMap = HashMap<String, List<Any>>()
        tempHashMap["Food"] = foodList
        tempHashMap["FavoriteFood"] = favoriteFoodList

        foodCardList.value = tempHashMap
    }

    fun searchFood(searchQuery: String) {
        val foodList = foodCardList.value?.get("Food") as List<Food>
        val favoriteFoodList = foodCardList.value?.get("FavoriteFood") as List<FavoriteFood>

        val filteredList = foodList.filter { it.foodName.lowercase().contains(searchQuery.lowercase()) }

        val tempHashMap = HashMap<String, List<Any>>()
        tempHashMap["Food"] = filteredList
        tempHashMap["FavoriteFood"] = favoriteFoodList

        foodCardList.value = tempHashMap
    }

    fun setFavorite(foodId: Int) {
        Log.e("Message", "Add favorite")
        loadFood()
    }
}
