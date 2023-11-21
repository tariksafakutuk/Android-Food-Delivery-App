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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.entity.CartFood
import com.example.foodie.databinding.FragmentProductDetailBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.viewmodel.ProductDetailViewModel
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val bundle: ProductDetailFragmentArgs by navArgs()
    private var isFavorite = false
    private lateinit var viewModel: ProductDetailViewModel
    @Inject lateinit var loginPref: LoginPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        binding.productDetailFragment = this

        isFavorite = bundle.isFavorite

        if (isFavorite) {
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_item))
        }

        binding.cartFoodObject = CartFood(1, bundle.food.foodName, bundle.food.foodImageName, bundle.food.foodPrice, 1, "test")

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${bundle.food.foodImageName}"
        Glide.with(this).load(url).into(binding.ivDetailFood)

        viewModel.cartFoodObject.observe(viewLifecycleOwner) {
            binding.cartFoodObject = it
            binding.invalidateAll()
        }

        viewModel.addCartStatus.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.tvDetailQuantity, "${bundle.food.foodName} ürünü sepete eklendi", Snackbar.LENGTH_SHORT).show()
                Navigation.changePage(binding.tvDetailQuantity, R.id.action_productDetailFragment_to_cartFragment)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun goToPreviousPage() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun changeFavoriteColor() {
        if (isFavorite) {
            viewModel.removeFavorite(bundle.food.foodId)
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_favorite))
        } else {
            viewModel.setFavorite(bundle.food.foodId, bundle.food.foodName, bundle.food.foodImageName, bundle.food.foodPrice)
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_item))
        }

        isFavorite = !isFavorite
    }

    fun foodQuantityChange(action: String, cartFoodObject: CartFood) {
        viewModel.foodQuantityChange(action, cartFoodObject, bundle.food.foodPrice)
    }

    fun addCart(cartFoodObject: CartFood) {
        CoroutineScope(Dispatchers.Main).launch {
            val username = loginPref.readUsername()
            viewModel.addToCart(
                cartFoodObject.foodName,
                cartFoodObject.foodImageName,
                cartFoodObject.foodPrice,
                cartFoodObject.foodQuantity,
                username
            )
        }
    }
}