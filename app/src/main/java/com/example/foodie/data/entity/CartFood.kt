package com.example.foodie.data.entity

data class CartFood(
    var cartFoodId: Int,
    var foodName: String,
    var foodImageName: String,
    var foodPrice: Int,
    var foodQuantity: Int,
    var username: String
) {
}