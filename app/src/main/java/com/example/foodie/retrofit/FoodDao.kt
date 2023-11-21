package com.example.foodie.retrofit

import com.example.foodie.data.entity.CRUDResponse
import com.example.foodie.data.entity.CartFoodResponse
import com.example.foodie.data.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFood(): FoodResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodImageName: String,
        @Field("yemek_fiyat") foodPrice: Int,
        @Field("yemek_siparis_adet") foodQuantity: Int,
        @Field("kullanici_adi") username: String
    ): CRUDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun loadCartFood(@Field("kullanici_adi") username: String): CartFoodResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteCartFood(
        @Field("sepet_yemek_id") cartFoodId: Int,
        @Field("kullanici_adi") username: String
    ): CRUDResponse
}