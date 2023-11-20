package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.databinding.FavoriteCardBinding
import com.example.foodie.ui.fragment.FavoritesFragmentDirections
import com.example.foodie.ui.viewmodel.FavoritesViewModel

class FavoriteCardAdapter(
    private var mContext: Context,
    private var favoriteFoodList: List<FavoriteFood>,
    private var viewModel: FavoritesViewModel,
    private var username: String
) : RecyclerView.Adapter<FavoriteCardAdapter.FavoriteCardHolder>() {

    inner class FavoriteCardHolder(var design: FavoriteCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCardHolder {
        val binding: FavoriteCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.favorite_card, parent, false)
        return FavoriteCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCardHolder, position: Int) {
        val favoriteFood = favoriteFoodList.get(position)
        val binding = holder.design

        binding.ivFavoriteFood.setImageResource(
            mContext.resources.getIdentifier(favoriteFood.foodImageName, "drawable", mContext.packageName)
        )

        binding.favoriteFoodObject = favoriteFood

        binding.ivFavoriteButton.setOnClickListener {
            viewModel.deleteFavoriteFood(favoriteFood.favoriteFoodId)
        }

        binding.buttonFavoritesCart.setOnClickListener {
            viewModel.addToCart(
                favoriteFood.foodName,
                favoriteFood.foodImageName,
                favoriteFood.foodPrice,
                1,
                username)
        }
    }

    override fun getItemCount(): Int {
        return favoriteFoodList.size
    }
}