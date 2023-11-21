package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.entity.CartFood
import com.example.foodie.databinding.CartCardBinding
import com.example.foodie.ui.viewmodel.CartViewModel

class CartCardAdapter(
    private var mContext: Context,
    private var cartFoodList: List<CartFood>,
    private var viewModel: CartViewModel,
    private var username: String
) : RecyclerView.Adapter<CartCardAdapter.CardCardHolder>() {

    inner class CardCardHolder(var design: CartCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCardHolder {
        val binding: CartCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.cart_card, parent, false)
        return CardCardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardCardHolder, position: Int) {
        val cartFood = cartFoodList.get(position)
        val binding = holder.design

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartFood.foodImageName}"
        Glide.with(mContext).load(url).into(binding.ivCartFood)

        binding.cartFoodObject = cartFood

        binding.ivCartDelete.setOnClickListener {
            viewModel.cartFoodAction(
                "Delete",
                cartFood.cartFoodId,
                cartFood.foodName,
                cartFood.foodImageName,
                cartFood.foodPrice,
                cartFood.foodQuantity,
                username
            )
        }

        binding.ivDecrease.setOnClickListener {
            cartFood.foodQuantity -= 1
            when (cartFood.foodQuantity) {
                0 -> viewModel.cartFoodAction(
                    "Delete",
                    cartFood.cartFoodId,
                    cartFood.foodName,
                    cartFood.foodImageName,
                    cartFood.foodPrice,
                    0,
                    username
                )

                else -> viewModel.cartFoodAction(
                    "Decrease",
                    cartFood.cartFoodId,
                    cartFood.foodName,
                    cartFood.foodImageName,
                    cartFood.foodPrice,
                    cartFood.foodQuantity,
                    username
                )
            }
        }

        binding.ivIncrease.setOnClickListener {
            cartFood.foodQuantity += 1
            viewModel.cartFoodAction(
                "Increase",
                cartFood.cartFoodId,
                cartFood.foodName,
                cartFood.foodImageName,
                cartFood.foodPrice,
                cartFood.foodQuantity,
                username
            )
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }
}