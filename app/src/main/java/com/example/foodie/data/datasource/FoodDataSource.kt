package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.entity.CartFood
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource {
    suspend fun loadFood(): HashMap<String, List<Any>> =
        withContext(Dispatchers.IO) {
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

            return@withContext tempHashMap
        }

    suspend fun searchFood(searchQuery: String, foodList: List<Food>, favoriteFoodList: List<FavoriteFood>): HashMap<String, List<Any>> =
        withContext(Dispatchers.IO) {
            val filteredList = foodList.filter { it.foodName.lowercase().contains(searchQuery.lowercase()) }

            val tempHashMap = HashMap<String, List<Any>>()
            tempHashMap["Food"] = filteredList
            tempHashMap["FavoriteFood"] = favoriteFoodList

            return@withContext tempHashMap
        }

    suspend fun loadCartFood(username: String): List<CartFood> =
        withContext(Dispatchers.IO) {
            val cartFoodList = ArrayList<CartFood>()
            val cf1 = CartFood(1, "Ayran", "ayran", 15, 2, "tariksafakutuk")
            val cf2 = CartFood(2, "Baklava", "ayran", 10, 3, "tariksafakutuk")
            cartFoodList.add(cf1)
            cartFoodList.add(cf2)

            return@withContext cartFoodList
        }

    suspend fun calculateTotalPrice(cartFoodList: List<CartFood>): String =
        withContext(Dispatchers.IO) {
            val cf1 = cartFoodList.get(0)
            val cf2 = cartFoodList.get(1)
            return@withContext ((cf1.foodPrice * cf1.foodQuantity) + (cf2.foodPrice * cf2.foodQuantity)).toString()
        }

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) {
        Log.e("Message", "Add to cart")
    }

    suspend fun deleteCartFood(cartFoodId: Int, username: String) {
        Log.e("Message", "Delete cart food - $username")
    }
}