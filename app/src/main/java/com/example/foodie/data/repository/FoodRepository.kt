package com.example.foodie.data.repository

import com.example.foodie.data.datasource.FoodDataSource
import com.example.foodie.data.entity.CartFood
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food

class FoodRepository(var fds: FoodDataSource) {
    suspend fun loadFood(): HashMap<String, List<Any>> = fds.loadFood()

    suspend fun searchFood(searchQuery: String, foodList: List<Food>, favoriteFoodList: List<FavoriteFood>): HashMap<String, List<Any>> =
        fds.searchFood(searchQuery, foodList, favoriteFoodList)

    suspend fun loadCartFood(username: String): List<CartFood> =
        fds.loadCartFood(username)

    suspend fun calculateTotalPrice(cartFoodList: List<CartFood>): String =
        fds.calculateTotalPrice(cartFoodList)

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String) =
        fds.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username)

    suspend fun deleteCartFood(cartFoodId: Int, username: String) =
        fds.deleteCartFood(cartFoodId, username)
}