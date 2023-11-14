package com.example.foodie.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.databinding.FavoriteCardBinding

class FavoriteCardAdapter(
    private var mContext: Context,
    private var favoriteFoodList: List<FavoriteFood>
) : RecyclerView.Adapter<FavoriteCardAdapter.FavoriteCardHolder>() {

    inner class FavoriteCardHolder(var design: FavoriteCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCardHolder {
        val binding = FavoriteCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FavoriteCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCardHolder, position: Int) {
        val favoriteFood = favoriteFoodList.get(position)
        val binding = holder.design

        binding.ivFavoriteFood.setImageResource(
            mContext.resources.getIdentifier(favoriteFood.foodImageName, "drawable", mContext.packageName)
        )

        binding.tvFavoriteFoodName.text = favoriteFood.foodName
        binding.tvFavoriteFoodPrice.text = "${favoriteFood.foodPrice} TL"

        binding.ivFavoriteButton.setOnClickListener {
            Log.e("Message", "Delete favorite food")
        }
    }

    override fun getItemCount(): Int {
        return favoriteFoodList.size
    }
}