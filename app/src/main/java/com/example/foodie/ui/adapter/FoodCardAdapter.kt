package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.Food
import com.example.foodie.databinding.FoodCardBinding

class FoodCardAdapter(private var mContext: Context, private var foodList: List<Food>) :
    RecyclerView.Adapter<FoodCardAdapter.FoodCardHolder>() {

    inner class FoodCardHolder(var design: FoodCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardHolder {
        val binding = FoodCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FoodCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardHolder, position: Int) {
        val food = foodList.get(position)
        val binding = holder.design

        binding.tvFoodName.text = food.foodName
        binding.tvFoodPrice.text = "${food.foodPrice} TL"

        binding.ivFood.setImageResource(
            mContext.resources.getIdentifier(food.foodImageName, "drawable", mContext.packageName)
        )

        binding.ivFavoriteButton.setOnClickListener {
            binding.ivFavoriteButton.setImageResource(
                mContext.resources.getIdentifier(R.drawable.vc_favorite.toString(), "drawable", mContext.packageName)
            )
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}