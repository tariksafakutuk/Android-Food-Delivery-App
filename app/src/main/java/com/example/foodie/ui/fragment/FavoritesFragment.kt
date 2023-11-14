package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvFavoriteCard.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val favoriteFoodList = ArrayList<FavoriteFood>()
        val f1 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        val f2 = FavoriteFood(1,1, "Ayran", "ayran", 15)
        favoriteFoodList.add(f1)
        favoriteFoodList.add(f2)

        val favoriteCardAdapter = FavoriteCardAdapter(requireContext(), favoriteFoodList)
        binding.rvFavoriteCard.adapter = favoriteCardAdapter

        return binding.root
    }
}