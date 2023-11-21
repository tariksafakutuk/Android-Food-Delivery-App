package com.example.foodie.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_food")
data class FavoriteFood(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "favorite_food_id") var favoriteFoodId: Int,
    @ColumnInfo(name = "food_id") var foodId: Int,
    @ColumnInfo(name = "food_name") var foodName: String,
    @ColumnInfo(name = "food_image_name") var foodImageName: String,
    @ColumnInfo(name = "food_price") var foodPrice: Int
)