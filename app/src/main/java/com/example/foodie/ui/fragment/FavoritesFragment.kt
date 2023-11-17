package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.databinding.FragmentFavoritesBinding
import com.example.foodie.ui.adapter.FavoriteCardAdapter

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val favoriteFoodList = ArrayList<FavoriteFood>()
        val f1 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        val f2 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        favoriteFoodList.add(f1)
        favoriteFoodList.add(f2)

        when (favoriteFoodList.size) {
            0 -> binding.hasFavoriteItem = false
            else -> {
                binding.hasFavoriteItem = true
                binding.favoriteCardAdapter = FavoriteCardAdapter(requireContext(), favoriteFoodList)
            }
        }

        return binding.root
    }
}