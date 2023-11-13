package com.example.foodie.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        val loginPref = LoginPref(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            val email = loginPref.readEmail()
            val username = loginPref.readUsername()
            val password = loginPref.readPassword()

            if ((email == "test@gmail.com" || username == "test") && password == "1234") {
                binding.ivLogin.visibility = View.GONE
                binding.tvLogin.visibility = View.GONE
                binding.tvAccountLogin.visibility = View.GONE
                binding.accountLoginLayout.visibility = View.GONE
                binding.tvPasswordLogin.visibility = View.GONE
                binding.passwordLoginLayout.visibility = View.GONE
                binding.buttonLogin.visibility = View.GONE
                binding.tvSignUpClick.visibility = View.GONE
                binding.tvProgressLogin.visibility = View.VISIBLE
                binding.progressBarLogin.visibility = View.VISIBLE

                delay(2000)

                clickView(binding.tvLogin, R.id.action_loginFragment_to_homePageFragment)
            }
        }

        binding.buttonLogin.setOnClickListener {
            val account = binding.editTextAccountLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()

            if (account == "test" || account == "test@gmail.com") {
                if (password == "1234") {
                    CoroutineScope(Dispatchers.Main).launch {
                        loginPref.createEmail("test@gmail.com")
                        loginPref.createUsername("test")
                        loginPref.createPassword("1234")

                        binding.ivLogin.visibility = View.GONE
                        binding.tvLogin.visibility = View.GONE
                        binding.tvAccountLogin.visibility = View.GONE
                        binding.accountLoginLayout.visibility = View.GONE
                        binding.tvPasswordLogin.visibility = View.GONE
                        binding.passwordLoginLayout.visibility = View.GONE
                        binding.buttonLogin.visibility = View.GONE
                        binding.tvSignUpClick.visibility = View.GONE
                        binding.tvProgressLogin.visibility = View.VISIBLE
                        binding.progressBarLogin.visibility = View.VISIBLE

                        delay(2000)

                        clickView(it, R.id.action_loginFragment_to_homePageFragment)
                    }
                } else {
                    Snackbar.make(it, "Şifre Hatalı", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(it, "Kullanıcı Adı veya Email Hatalı", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.tvSignUpClick.setOnClickListener {
            clickView(it, R.id.action_loginFragment_to_signUpFragment)
        }

        return binding.root
    }

    fun clickView(view: View, id: Int) {
        Navigation.changePage(view, id)
    }
}