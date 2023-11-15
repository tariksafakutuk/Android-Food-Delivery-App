package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodie.R
import com.example.foodie.data.entity.AccountCardItem
import com.example.foodie.databinding.FragmentAccountBinding
import com.example.foodie.ui.adapter.AccountCardAdapter

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.rvAccountCard.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        val accountCardItemList = ArrayList<AccountCardItem>()
        val a1 = AccountCardItem("vc_email", "Email Değişikliği")
        val a2 = AccountCardItem("vc_password", "Şifre Değişikliği")
        val a3 = AccountCardItem("vc_logout", "Çıkış")
        accountCardItemList.add(a1)
        accountCardItemList.add(a2)
        accountCardItemList.add(a3)

        val accountCardAdapter = AccountCardAdapter(requireContext(), accountCardItemList)
        binding.rvAccountCard.adapter = accountCardAdapter

        return binding.root
    }
}