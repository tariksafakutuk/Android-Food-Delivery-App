package com.example.foodie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.AccountCardItem
import com.example.foodie.databinding.AccountCardBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.fragment.AccountFragmentDirections
import com.example.foodie.utils.changePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountCardAdapter(private var mContext: Context, private var accountCardItemList: List<AccountCardItem>) :
    RecyclerView.Adapter<AccountCardAdapter.AccountCardHolder>() {

    inner class AccountCardHolder(var design: AccountCardBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountCardHolder {
        val binding: AccountCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.account_card, parent, false)
        return AccountCardHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountCardHolder, position: Int) {
        val accountCardItem = accountCardItemList.get(position)
        val binding = holder.design

        binding.ivAccountCard.setImageResource(
            mContext.resources.getIdentifier(accountCardItem.itemImageName, "drawable", mContext.packageName)
        )

        binding.itemTitle = accountCardItem.itemTitle

        binding.cardViewAccount.setOnClickListener {
            when (position) {
                0 -> {
                    val direction = AccountFragmentDirections.actionAccountFragmentToAccountDetailFragment(action = "email")
                    Navigation.changePage(it, direction)
                }

                1 -> {
                    val direction = AccountFragmentDirections.actionAccountFragmentToAccountDetailFragment(action = "password")
                    Navigation.changePage(it, direction)
                }

                2 -> {
                    val loginPref = LoginPref(mContext)

                    CoroutineScope(Dispatchers.Main).launch {
                        loginPref.deleteEmail()
                        loginPref.deleteUsername()
                        loginPref.deletePassword()
                        Navigation.changePage(it, R.id.action_accountFragment_to_loginFragment)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return accountCardItemList.size
    }
}