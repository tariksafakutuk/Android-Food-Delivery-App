package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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

        binding.ivCartFood.setImageResource(
            mContext.resources.getIdentifier(cartFood.foodImageName, "drawable", mContext.packageName)
        )

        binding.cartFoodObject = cartFood

        binding.ivCartDelete.setOnClickListener {
            viewModel.deleteCartFood(cartFood.cartFoodId, username)
        }

        binding.ivDecrease.setOnClickListener {
            viewModel.quantityAction("Decrease", cartFood.cartFoodId, username)
        }

        binding.ivIncrease.setOnClickListener {
            viewModel.quantityAction("Increase", cartFood.cartFoodId, username)
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }
}