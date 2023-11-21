package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName

data class CartFoodResponse(
    @SerializedName("sepet_yemekler") var cartFoodList: List<CartFood>,
    @SerializedName("success") var success: Int
)