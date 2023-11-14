package com.example.foodie.data.entity

import java.io.Serializable

data class Food(var foodId: Int,
                var foodName: String,
                var foodImageName: String,
                var foodPrice: Int): Serializable {
}