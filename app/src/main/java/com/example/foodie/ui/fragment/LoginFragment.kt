package com.example.foodie.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.databinding.FragmentLoginBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.viewmodel.LoginViewModel
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginPref: LoginPref
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginFragment = this

        loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            val email = loginPref.readEmail()
            val username = loginPref.readUsername()
            val password = loginPref.readPassword()

            viewModel.isLoginCheck(email, username, password)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.loginStatus = it

                if (it == "login") {
                    delay(2000)
                    Navigation.changePage(binding.tvLogin, R.id.action_loginFragment_to_homePageFragment)
                }
            }
        }

        viewModel.userData.observe(viewLifecycleOwner) {
            when (it[0]) {
                "passwordFailed" -> {
                    Snackbar.make(binding.tvLogin, "Şifre Hatalı", Snackbar.LENGTH_SHORT).show()
                }

                "accountFailed" -> {
                    Snackbar.make(binding.tvLogin, "Kullanıcı Adı veya Email Hatalı", Snackbar.LENGTH_SHORT).show()
                }

                else -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        loginPref.createEmail(it[0])
                        loginPref.createUsername(it[1])
                        loginPref.createPassword(it[2])
                    }
                }
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun clickLoginButton(view: View, account: String, password: String) {
        viewModel.login(account, password)
    }

    fun clickTextView(view: View) {
        Navigation.changePage(view, R.id.action_loginFragment_to_signUpFragment)
    }
}