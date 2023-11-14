package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodie.R
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
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            binding.tvHeaderName.text =
                resources.getString(R.string.header_name, loginPref.readUsername())
        }

        binding.rvFoodCard.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val foodCardList = ArrayList<Food>()
        val f1 = Food(1, "Ayran", "ic_launcher", 15)
        val f2 = Food(2, "Baklava", "ic_launcher", 15)
        foodCardList.add(f1)
        foodCardList.add(f2)

        val foodCardAdapter = FoodCardAdapter(requireContext(), foodCardList)
        binding.rvFoodCard.adapter = foodCardAdapter

        return binding.root
    }
}