package com.example.foodie.data.repository

import com.example.foodie.data.datasource.FoodDataSource
import com.example.foodie.data.entity.CartFood

class FoodRepository(var fds: FoodDataSource) {
    suspend fun loadFood(): HashMap<String, List<Any>> = fds.loadFood()

    suspend fun searchFood(searchQuery: String): HashMap<String, List<Any>> =
        fds.searchFood(searchQuery)

    suspend fun loadCartFood(username: String): List<CartFood> =
        fds.loadCartFood(username)

    suspend fun calculateTotalPrice(cartFoodList: List<CartFood>): String =
        fds.calculateTotalPrice(cartFoodList)

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, username: String, action: String) =
        fds.addToCart(foodName, foodImageName, foodPrice, foodQuantity, username, action)

    suspend fun deleteCartFood(cartFoodId: Int, username: String) =
        fds.deleteCartFood(cartFoodId, username)

    suspend fun confirmCartTotal(cartFoodList: List<CartFood>, username: String) =
        fds.confirmCartTotal(cartFoodList, username)
}