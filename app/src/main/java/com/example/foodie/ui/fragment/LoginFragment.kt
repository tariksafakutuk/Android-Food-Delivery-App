package com.example.foodie.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.databinding.FragmentLoginBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginPref: LoginPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginFragment = this
        binding.loginStatus = "loading"

        loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            val email = loginPref.readEmail()
            val username = loginPref.readUsername()
            val password = loginPref.readPassword()

            if ((email == "test@gmail.com" || username == "test") && password == "1234") {
                binding.loginStatus = "login"
                delay(2000)
                Navigation.changePage(binding.tvLogin, R.id.action_loginFragment_to_homePageFragment)
            } else {
                binding.loginStatus = "loginFailed"
            }
        }

        return binding.root
    }

    fun clickLoginButton(view: View, account: String, password: String) {
        if (account == "test" || account == "test@gmail.com") {
            if (password == "1234") {
                CoroutineScope(Dispatchers.Main).launch {
                    loginPref.createEmail("test@gmail.com")
                    loginPref.createUsername("test")
                    loginPref.createPassword("1234")

                    binding.loginStatus = "login"
                    delay(2000)
                    Navigation.changePage(view, R.id.action_loginFragment_to_homePageFragment)

                }
            } else {
                Snackbar.make(view, "Şifre Hatalı", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            Snackbar.make(view, "Kullanıcı Adı veya Email Hatalı", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun clickTextView(view: View) {
        Navigation.changePage(view, R.id.action_loginFragment_to_signUpFragment)
    }
}