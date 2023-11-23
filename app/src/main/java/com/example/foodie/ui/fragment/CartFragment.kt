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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    @Inject lateinit var loginPref: LoginPref
    private lateinit var viewModel: CartViewModel
    private var username = ""
    private var isConfirmOrder = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.cartFragment = this

        CoroutineScope(Dispatchers.Main).launch {
            username = loginPref.readUsername()
            viewModel.loadCartFood(username)
        }

        viewModel.cartFoodCardList.observe(viewLifecycleOwner) {
            when (it.size) {
                0 -> {
                    if (isConfirmOrder) {
                        binding.visibilityAction = "orderTaken"
                        binding.avOrderAnimation.setAnimation(R.raw.order)
                        binding.avOrderAnimation.playAnimation()
                    } else {
                        binding.visibilityAction = "noData"
                    }
                }

                else -> {
                    binding.visibilityAction = "hasCartItem"
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
                CoroutineScope(Dispatchers.Main).launch {
                    val username = loginPref.readUsername()
                    isConfirmOrder = true
                    viewModel.confirmCartTotal(username)
                }
            }
            .show()
    }
}