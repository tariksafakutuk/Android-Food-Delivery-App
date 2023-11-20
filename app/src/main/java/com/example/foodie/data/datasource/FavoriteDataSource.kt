package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.entity.FavoriteFood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteDataSource {
    suspend fun loadFavoriteFood(): List<FavoriteFood> =
        withContext(Dispatchers.IO) {
            val favoriteFoodList = ArrayList<FavoriteFood>()
            val f1 = FavoriteFood(1,1, "Ayran", "ayran", 15)
            val f2 = FavoriteFood(1,1, "Ayran", "ayran", 15)
            favoriteFoodList.add(f1)
            favoriteFoodList.add(f2)

            return@withContext favoriteFoodList
        }

    suspend fun addFavoriteFood(foodId: Int, foodName: String, foodImageName: String, foodPrice: Int) {
        Log.e("Message", "Add favorite food")
    }

    suspend fun deleteFavoriteFood(foodId: Int) {
        Log.e("Message", "Delete favorite food")
    }
}