package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.foodie.R
import com.example.foodie.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private val bundle: ProductDetailFragmentArgs by navArgs()
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        isFavorite = bundle.isFavorite

        if (isFavorite) {
            binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_item))
        }

        binding.ivDetailFavoriteButton.setOnClickListener {
            if (isFavorite) {
                binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_favorite))
            } else {
                binding.ivDetailFavoriteButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.product_detail_item))
            }

            isFavorite = !isFavorite
        }

        binding.ivDetailFood.setImageResource(
            resources.getIdentifier(bundle.food.foodImageName, "drawable", requireContext().packageName)
        )

        binding.tvDetailFoodName.text = bundle.food.foodName
        binding.tvDetailPrice.text = "${bundle.food.foodPrice} TL"
        binding.tvDetailQuantity.text = "1"

        binding.ivDetailDecrease.setOnClickListener {
            var lastQuantity = binding.tvDetailQuantity.text.toString().toInt()
            if (lastQuantity != 1) {
                lastQuantity -= 1
                binding.tvDetailQuantity.text = lastQuantity.toString()
                binding.tvDetailPrice.text = "${lastQuantity * bundle.food.foodPrice} TL"
            }
        }

        binding.ivDetailIncrease.setOnClickListener {
            val lastQuantity = binding.tvDetailQuantity.text.toString().toInt() + 1
            binding.tvDetailQuantity.text = (lastQuantity).toString()
            binding.tvDetailPrice.text = "${lastQuantity * bundle.food.foodPrice} TL"
        }

        return binding.root
    }
}