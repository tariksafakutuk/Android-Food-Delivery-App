package com.example.foodie.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDao(): FoodDao {
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }
    }
}