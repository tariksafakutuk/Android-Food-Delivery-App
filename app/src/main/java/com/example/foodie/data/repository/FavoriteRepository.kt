package com.example.foodie.data.repository

import com.example.foodie.data.datasource.FavoriteDataSource
import com.example.foodie.data.entity.FavoriteFood

class FavoriteRepository {
    private val fds = FavoriteDataSource()

    suspend fun loadFavoriteFood(): List<FavoriteFood> =
        fds.loadFavoriteFood()

    suspend fun addFavoriteFood(foodId: Int, foodName: String, foodImageName: String, foodPrice: Int) =
        fds.addFavoriteFood(foodId, foodName, foodImageName, foodPrice)

    suspend fun deleteFavoriteFood(foodId: Int) =
        fds.deleteFavoriteFood(foodId)
}