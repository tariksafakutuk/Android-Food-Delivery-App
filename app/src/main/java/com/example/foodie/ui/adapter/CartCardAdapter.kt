package com.example.foodie.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.CartFood
import com.example.foodie.databinding.CartCardBinding

class CartCardAdapter(private var mContext: Context, private var cartFoodList: List<CartFood>) :
    RecyclerView.Adapter<CartCardAdapter.CardCardHolder>() {

    inner class CardCardHolder(var design: CartCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCardHolder {
        val binding = CartCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardCardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardCardHolder, position: Int) {
        val cartFood = cartFoodList.get(position)
        val binding = holder.design

        binding.ivCartFood.setImageResource(
            mContext.resources.getIdentifier(cartFood.foodImageName, "drawable", mContext.packageName)
        )

        binding.tvCartFoodName.text = cartFood.foodName
        binding.tvCartFoodPrice.text = "${cartFood.foodPrice} TL"
        binding.tvQuantity.text = cartFood.foodQuantity.toString()

        binding.ivCartDelete.setOnClickListener {
            Log.e("Message", "Delete cart")
        }

        binding.ivDecrease.setOnClickListener {
            Log.e("Message", "Decrease quantity")
        }

        binding.ivIncrease.setOnClickListener {
            Log.e("Message", "Increase quantity")
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }
}