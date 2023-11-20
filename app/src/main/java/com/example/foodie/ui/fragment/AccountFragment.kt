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
import com.example.foodie.databinding.FragmentAccountBinding
import com.example.foodie.ui.adapter.AccountCardAdapter
import com.example.foodie.ui.viewmodel.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        viewModel.accountCardList.observe(viewLifecycleOwner) {
            binding.accountCardAdapter = AccountCardAdapter(requireContext(), it)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AccountViewModel by viewModels()
        viewModel = tempViewModel
    }
}