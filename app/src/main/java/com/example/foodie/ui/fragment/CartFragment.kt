package com.example.foodie.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.rvCartCard.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val cartFoodList = ArrayList<CartFood>()
        val cf1 = CartFood(1, "Ayran", "ayran", 15, 2, "tariksafakutuk")
        val cf2 = CartFood(2, "Baklava", "ayran", 10, 3, "tariksafakutuk")
        cartFoodList.add(cf1)
        cartFoodList.add(cf2)

        if (cartFoodList.size == 0) {
            binding.rvCartCard.visibility = View.GONE
            binding.linearLayoutTotal.visibility = View.GONE
            binding.ivCartNoData.visibility = View.VISIBLE
            binding.tvCartNoData.visibility = View.VISIBLE
        } else {
            binding.rvCartCard.visibility = View.VISIBLE
            binding.linearLayoutTotal.visibility = View.VISIBLE
            binding.ivCartNoData.visibility = View.GONE
            binding.tvCartNoData.visibility = View.GONE

            val cartCardAdapter = CartCardAdapter(requireContext(), cartFoodList)
            binding.rvCartCard.adapter = cartCardAdapter

            binding.tvCartTotal.text = "${(cf1.foodPrice * cf1.foodQuantity) + (cf2.foodPrice * cf2.foodQuantity)} TL"

            binding.buttonCartTotal.setOnClickListener {
                Log.e("Message", "Delete cart total")
            }
        }

        return binding.root
    }
}