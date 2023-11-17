package com.example.foodie.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.foodie.R
import com.example.foodie.data.entity.CartFood
import com.example.foodie.databinding.FragmentCartBinding
import com.example.foodie.ui.adapter.CartCardAdapter

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.cartFragment = this

        val cartFoodList = ArrayList<CartFood>()
        val cf1 = CartFood(1, "Ayran", "ayran", 15, 2, "tariksafakutuk")
        val cf2 = CartFood(2, "Baklava", "ayran", 10, 3, "tariksafakutuk")
        cartFoodList.add(cf1)
        cartFoodList.add(cf2)

        when (cartFoodList.size) {
            0 -> binding.hasCartItem = false
            else -> {
                binding.hasCartItem = true
                binding.cartCardAdapter = CartCardAdapter(requireContext(), cartFoodList)
                binding.totalPrice = ((cf1.foodPrice * cf1.foodQuantity) + (cf2.foodPrice * cf2.foodQuantity)).toString()
            }
        }

        return binding.root
    }

    fun confirmCartTotal() {
        Log.e("Message", "Confirm cart total")
    }
}