package com.example.foodie.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.foodie.data.entity.FavoriteFood

@Dao
interface FavoriteFoodDao {
    @Query("SELECT * FROM favorite_food ORDER BY food_name")
    suspend fun loadFavoriteFood(): List<FavoriteFood>

    @Insert
    suspend fun addFavoriteFood(favoriteFood: FavoriteFood)

    @Delete
    suspend fun deleteFavoriteFood(favoriteFood: FavoriteFood)
}