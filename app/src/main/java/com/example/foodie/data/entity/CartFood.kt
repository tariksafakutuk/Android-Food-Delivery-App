package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName

data class CartFood(
    @SerializedName("sepet_yemek_id") var cartFoodId: Int,
    @SerializedName("yemek_adi") var foodName: String,
    @SerializedName("yemek_resim_adi") var foodImageName: String,
    @SerializedName("yemek_fiyat") var foodPrice: Int,
    @SerializedName("yemek_siparis_adet") var foodQuantity: Int,
    @SerializedName("kullanici_adi") var username: String
)