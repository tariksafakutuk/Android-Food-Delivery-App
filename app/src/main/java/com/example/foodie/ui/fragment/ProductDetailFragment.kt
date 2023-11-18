package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodie.R
import com.example.foodie.data.entity.CartFood
import com.example.foodie.databinding.FragmentProductDetailBinding
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val bundle: ProductDetailFragmentArgs by navArgs()
    private var isFavorite = false

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

        binding.ivDetailFood.setImageResource(
            resources.getIdentifier(bundle.food.foodImageName, "drawable", requireContext().packageName)
        )

        binding.cartFoodObject = CartFood(1, bundle.food.foodName, bundle.food.foodImageName, bundle.food.foodPrice, 1, "test")

        return binding.root
    }

    fun goToPreviousPage() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun changeFavoriteColor() {
        if (isFavorite) {
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_favorite))
        } else {
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_item))
        }

        isFavorite = !isFavorite
    }

    fun foodQuantityChange(action: String) {
        val cardFoodObject = binding.cartFoodObject

        if (cardFoodObject != null) {
            when (action) {
                "Decrease" -> {
                    if (cardFoodObject.foodQuantity != 1) {
                        cardFoodObject.foodQuantity -= 1
                    }
                }

                "Increase" -> {
                    cardFoodObject.foodQuantity += 1
                }
            }
            cardFoodObject.foodPrice = cardFoodObject.foodQuantity * bundle.food.foodPrice
            binding.cartFoodObject = cardFoodObject
            binding.invalidateAll()
        }
    }

    fun addCart(view: View, cartFoodObject: CartFood) {
        Snackbar.make(view, "${cartFoodObject.foodName} ürünü sepete eklendi", Snackbar.LENGTH_SHORT).show()
        Navigation.changePage(view, R.id.action_productDetailFragment_to_cartFragment)
    }
}