package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.databinding.FragmentHomePageBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.adapter.FoodCardAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        val loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            binding.username = resources.getString(R.string.header_name, loginPref.readUsername())
        }

        val foodCardList = ArrayList<Food>()
        val f1 = Food(1, "Ayran", "ayran", 15)
        val f2 = Food(2, "Baklava", "ayran", 15)
        foodCardList.add(f1)
        foodCardList.add(f2)

        val favoriteFoodList = ArrayList<FavoriteFood>()
        val ff1 = FavoriteFood(1, 2, "Baklava", "ayran", 15)
        favoriteFoodList.add(ff1)

        binding.foodCardAdapter = FoodCardAdapter(requireContext(), foodCardList, favoriteFoodList)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchFood(query, foodCardList, favoriteFoodList)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchFood(newText, foodCardList, favoriteFoodList)
                return true
            }

        })
        return binding.root
    }

    fun searchFood(searchQuery: String, foodCardList: ArrayList<Food>, favoriteFoodList: ArrayList<FavoriteFood>) {
        val filteredList = foodCardList.filter { it.foodName.lowercase().contains(searchQuery.lowercase()) }
        binding.foodCardAdapter = FoodCardAdapter(requireContext(), filteredList, favoriteFoodList)
    }
}