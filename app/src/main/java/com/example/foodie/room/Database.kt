package com.example.foodie.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.data.entity.FavoriteFood

@Database(entities = [FavoriteFood::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getFavoriteFoodDao(): FavoriteFoodDao
}