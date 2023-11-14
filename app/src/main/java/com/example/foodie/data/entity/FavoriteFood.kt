package com.example.foodie.data.entity

data class FavoriteFood(var favoriteFoodId: Int,
                        var foodId: Int,
                        var foodName: String,
                        var foodImageName: String,
                        var foodPrice: Int) {
}