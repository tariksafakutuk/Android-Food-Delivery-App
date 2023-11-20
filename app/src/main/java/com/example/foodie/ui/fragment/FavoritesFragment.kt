package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.databinding.FragmentFavoritesBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.adapter.FavoriteCardAdapter
import com.example.foodie.ui.viewmodel.FavoritesViewModel
import com.example.foodie.utils.changePage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    @Inject lateinit var loginPref: LoginPref
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        viewModel.favoriteFoodCardList.observe(viewLifecycleOwner) {
            when (it.size) {
                0 -> binding.hasFavoriteItem = false
                else -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        val username = loginPref.readUsername()
                        binding.hasFavoriteItem = true
                        binding.favoriteCardAdapter = FavoriteCardAdapter(requireContext(), it, viewModel, username)
                    }
                }
            }
        }

        viewModel.addCartStatus.observe(viewLifecycleOwner) {
            if (it) {
                Navigation.changePage(binding.tvFavoriteTitle, R.id.action_favoritesFragment_to_cartFragment)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoritesViewModel by viewModels()
        viewModel = tempViewModel
    }
}