package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodie.R
import com.example.foodie.data.entity.FavoriteFood
import com.example.foodie.data.entity.Food
import com.example.foodie.databinding.FragmentHomePageBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.adapter.FoodCardAdapter
import com.example.foodie.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    @Inject lateinit var loginPref: LoginPref
    private var isAgainCreate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        CoroutineScope(Dispatchers.Main).launch {
            binding.username = resources.getString(R.string.header_name, loginPref.readUsername())
        }

        viewModel.foodCardList.observe(viewLifecycleOwner) {
            binding.foodCardAdapter = FoodCardAdapter(
                requireContext(),
                it["Food"] as List<Food>,
                it["FavoriteFood"] as List<FavoriteFood>,
                viewModel
            )
            binding.rvFoodCard.setItemViewCacheSize(it["Food"]!!.size)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!isAgainCreate) {
                    viewModel.searchFood(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!isAgainCreate) {
                    viewModel.searchFood(newText)
                }
                return true
            }

        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        if (isAgainCreate) {
            binding.searchView.setQuery("", true)
            binding.foodCardAdapter = null
            viewModel.loadFood()
            isAgainCreate = false
        }
    }

    override fun onPause() {
        super.onPause()
        isAgainCreate = true
    }
}