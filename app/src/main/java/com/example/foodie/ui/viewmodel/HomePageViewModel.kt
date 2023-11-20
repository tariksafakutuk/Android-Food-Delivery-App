package com.example.foodie.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.data.repository.FoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel: ViewModel() {
    private val foodRepo = FoodRepository()

    private val _foodCardList = MutableLiveData<HashMap<String, List<Any>>>()
    val foodCardList: MutableLiveData<HashMap<String, List<Any>>> = _foodCardList

    init {
        loadFood()
    }

    fun loadFood() {
        CoroutineScope(Dispatchers.Main).launch {
            _foodCardList.value = foodRepo.loadFood()
        }
    }

    fun searchFood(searchQuery: String) {
        val foodList = _foodCardList.value?.get("Food") as List<Food>
        val favoriteFoodList = _foodCardList.value?.get("FavoriteFood") as List<FavoriteFood>

        CoroutineScope(Dispatchers.Main).launch {
            _foodCardList.value = foodRepo.searchFood(searchQuery, foodList, favoriteFoodList)
        }
    }

    fun setFavorite(foodId: Int) {
        Log.e("Message", "Add favorite")
        loadFood()
    }
}
