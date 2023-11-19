package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodie.R
import com.example.foodie.databinding.FragmentCartBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.adapter.CartCardAdapter
import com.example.foodie.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var loginPref: LoginPref
    private lateinit var viewModel: CartViewModel
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.cartFragment = this

        loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            username = loginPref.readUsername()
            viewModel.loadCartFood(username)
            viewModel.calculateTotalPrice()

        }

        viewModel.cartFoodCardList.observe(viewLifecycleOwner) {
            when (it.size) {
                0 -> binding.hasCartItem = false
                else -> {
                    binding.hasCartItem = true
                    binding.cartCardAdapter = CartCardAdapter(requireContext(), it, viewModel, username)
                }
            }
        }

        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice = it
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun confirmCartTotal() {
        Snackbar.make(binding.tvCartTotal, "Sepeti onaylamak istediğinize emin misiniz?", Snackbar.LENGTH_SHORT)
            .setAction("Evet") {
                viewModel.confirmCartTotal()
            }
            .show()
    }
}