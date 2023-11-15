package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.AccountCardItem
import com.example.foodie.databinding.AccountCardBinding

class AccountCardAdapter(private var mContext: Context, private var accountCardItemList: List<AccountCardItem>) :
    RecyclerView.Adapter<AccountCardAdapter.AccountCardHolder>() {

    inner class AccountCardHolder(var design: AccountCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountCardHolder {
        val binding = AccountCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return AccountCardHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountCardHolder, position: Int) {
        val accountCardItem = accountCardItemList.get(position)
        val binding = holder.design

        binding.ivAccountCard.setImageResource(
            mContext.resources.getIdentifier(accountCardItem.itemImageName, "drawable", mContext.packageName)
        )

        binding.tvAccountCard.text = accountCardItem.itemTitle
    }

    override fun getItemCount(): Int {
        return accountCardItemList.size
    }
}