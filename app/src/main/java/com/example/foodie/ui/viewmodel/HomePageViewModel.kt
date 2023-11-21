package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.FavoriteRepository
import com.example.foodie.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var foodRepo: FoodRepository, var favRepo: FavoriteRepository): ViewModel() {
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
        CoroutineScope(Dispatchers.Main).launch {
            _foodCardList.value = foodRepo.searchFood(searchQuery)
        }
    }

    fun setFavorite(foodId: Int, foodName: String, foodImageName: String, foodPrice: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            favRepo.addFavoriteFood(foodId, foodName, foodImageName, foodPrice)
        }
    }

    fun removeFavorite(foodId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            favRepo.deleteFavoriteFood(foodId)
        }
    }
}
