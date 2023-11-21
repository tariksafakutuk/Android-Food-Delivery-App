package com.example.foodie.data.datasource

import com.example.foodie.data.entity.CartFood
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao: FoodDao) {
    suspend fun loadFood(): HashMap<String, List<Any>> =
        withContext(Dispatchers.IO) {
            val foodList = fdao.loadFood().foodList

            val favoriteFoodList = ArrayList<FavoriteFood>()
            val ff1 = FavoriteFood(1, 2, "Baklava", "ayran", 15)
            favoriteFoodList.add(ff1)

            val tempHashMap = HashMap<String, List<Any>>()
            tempHashMap["Food"] = foodList
            tempHashMap["FavoriteFood"] = favoriteFoodList

            return@withContext tempHashMap
        }

    suspend fun searchFood(
        searchQuery: String,
    ): HashMap<String, List<Any>> =
        withContext(Dispatchers.IO) {
            val foodResponse = loadFood()
            val foodList = foodResponse["Food"] as List<Food>

            val filteredList =
                foodList.filter { it.foodName.lowercase().contains(searchQuery.lowercase()) }

            val tempHashMap = HashMap<String, List<Any>>()
            tempHashMap["Food"] = filteredList
            tempHashMap["FavoriteFood"] = foodResponse["FavoriteFood"] as List<FavoriteFood>

            return@withContext tempHashMap
        }

    suspend fun loadCartFood(username: String): List<CartFood> =
        withContext(Dispatchers.IO) {
            addToCart("error", "error",0,0, username)
            val tempFoodList = fdao.loadCartFood(username).cartFoodList
            var errorFoodId = 0
            val cartFoodList = ArrayList<CartFood>()

            tempFoodList.forEach {
                if (it.foodName == "error") {
                    errorFoodId = it.cartFoodId
                } else {
                    cartFoodList.add(it)
                }
            }
            deleteCartFood(errorFoodId, username)

            cartFoodList.sortBy { it.foodName }

            return@withContext cartFoodList
        }

    suspend fun calculateTotalPrice(cartFoodList: List<CartFood>): String =
        withContext(Dispatchers.IO) {
            var totalPrice = 0
            cartFoodList.forEach { cartFood ->
                totalPrice += (cartFood.foodPrice * cartFood.foodQuantity)
            }
            return@withContext totalPrice.toString()
        }

    suspend fun addToCart(
        foodName: String,
        foodImageName: String,
        foodPrice: Int,
        foodQuantity: Int,
        username: String
    ) = fdao.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username)

    suspend fun deleteCartFood(cartFoodId: Int, username: String) =
        fdao.deleteCartFood(cartFoodId, username)

    suspend fun confirmCartTotal(cartFoodList: List<CartFood>, username: String) {
        cartFoodList.forEach { cartFood ->
            deleteCartFood(cartFood.cartFoodId, username)
        }
    }
}