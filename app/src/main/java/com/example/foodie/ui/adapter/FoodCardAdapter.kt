package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.databinding.FoodCardBinding
import com.example.foodie.ui.fragment.HomePageFragmentDirections
import com.example.foodie.ui.viewmodel.HomePageViewModel
import com.example.foodie.utils.changePage

class FoodCardAdapter(
    private var mContext: Context,
    private var foodList: List<Food>,
    private var favoriteFoodList: List<FavoriteFood>,
    private var viewModel: HomePageViewModel
) : RecyclerView.Adapter<FoodCardAdapter.FoodCardHolder>() {

    inner class FoodCardHolder(var design: FoodCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardHolder {
        val binding: FoodCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.food_card, parent, false)
        return FoodCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardHolder, position: Int) {
        val food = foodList.get(position)
        val binding = holder.design
        var isFavorite = false

        binding.foodObject = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodImageName}"
        Glide.with(mContext).load(url).into(binding.ivFood)

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
            if (isFavorite) {
                viewModel.removeFavorite(food.foodId)
                binding.ivFavoriteButton.setImageResource(
                    mContext.resources.getIdentifier(R.drawable.vc_favorite_border.toString(), "drawable", mContext.packageName)
                )
            } else {
                viewModel.setFavorite(food.foodId, food.foodName, food.foodImageName, food.foodPrice)
                binding.ivFavoriteButton.setImageResource(
                    mContext.resources.getIdentifier(R.drawable.vc_favorite.toString(), "drawable", mContext.packageName)
                )
            }

            isFavorite = !isFavorite
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