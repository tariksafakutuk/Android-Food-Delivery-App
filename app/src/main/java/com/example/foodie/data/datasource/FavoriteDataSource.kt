package com.example.foodie.data.datasource

import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.room.FavoriteFoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteDataSource(var favDao: FavoriteFoodDao) {
    suspend fun loadFavoriteFood(): List<FavoriteFood> =
        withContext(Dispatchers.IO) {
            return@withContext favDao.loadFavoriteFood()
        }

    suspend fun addFavoriteFood(
        foodId: Int,
        foodName: String,
        foodImageName: String,
        foodPrice: Int
    ) {
        val newFavoriteFood = FavoriteFood(0, foodId, foodName, foodImageName, foodPrice)
        favDao.addFavoriteFood(newFavoriteFood)
    }

    suspend fun deleteFavoriteFood(foodId: Int) {
        val favoriteFoodList = loadFavoriteFood()
        favoriteFoodList.forEach { favoriteFood ->
            if (favoriteFood.foodId == foodId) {
                val deletedFavoriteFood = FavoriteFood(
                    favoriteFood.favoriteFoodId,
                    favoriteFood.foodId,
                    favoriteFood.foodName,
                    favoriteFood.foodImageName,
                    favoriteFood.foodPrice
                )
                favDao.deleteFavoriteFood(deletedFavoriteFood)
            }
        }
    }
}