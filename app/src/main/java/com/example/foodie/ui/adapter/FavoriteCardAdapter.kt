package com.example.foodie.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.databinding.FavoriteCardBinding
import com.example.foodie.ui.fragment.FavoritesFragmentDirections
import com.example.foodie.utils.changePage

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

        binding.buttonFavoritesCart.setOnClickListener {
            Navigation.changePage(it, R.id.action_favoritesFragment_to_cartFragment)
        }
    }

    override fun getItemCount(): Int {
        return favoriteFoodList.size
    }
}