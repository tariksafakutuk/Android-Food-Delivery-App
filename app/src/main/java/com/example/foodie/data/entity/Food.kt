package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Food(
    @SerializedName("yemek_id") var foodId: Int,
    @SerializedName("yemek_adi") var foodName: String,
    @SerializedName("yemek_resim_adi") var foodImageName: String,
    @SerializedName("yemek_fiyat") var foodPrice: Int
) : Serializable