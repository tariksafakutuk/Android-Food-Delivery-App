package com.example.foodie.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.databinding.FoodCardBinding
import com.example.foodie.ui.fragment.HomePageFragmentDirections
import com.example.foodie.utils.changePage

class FoodCardAdapter(private var mContext: Context, private var foodList: List<Food>, private var favoriteFoodList: List<FavoriteFood>) :
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
        var isFavorite = false

        binding.foodObject = food

        binding.ivFood.setImageResource(
            mContext.resources.getIdentifier(food.foodImageName, "drawable", mContext.packageName)
        )

        favoriteFoodList.forEach { favoriteFood ->
            if (favoriteFood.foodId == food.foodId) {
                isFavorite = true
            }
        }

        if (isFavorite) {
            binding.ivFavoriteButton.setImageResource(
                mContext.resources.getIdentifier(R.drawable.vc_favorite.toString(), "drawable", mContext.packageName)
            )
        }

        binding.ivFavoriteButton.setOnClickListener {
            Log.e("Message", "Add favorite")
        }

        binding.foodCard.setOnClickListener {
            val direction = HomePageFragmentDirections.actionHomePageFragmentToProductDetailFragment(food = food, isFavorite = isFavorite)
            Navigation.changePage(it, direction)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}