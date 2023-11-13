package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSignUpBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var loginPref: LoginPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        loginPref = LoginPref(requireContext())

        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmailSignUp.text.toString()
            val username = binding.editTextUsernameSignUp.text.toString()
            val password = binding.editTextPasswordSignUp.text.toString()

            if (email != "" && username != "" && password != "") {
                clickButton(it, email, username, password)
            } else {
                Snackbar.make(it, "Lütfen Tüm Alanları Doldurunuz!", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.tvLoginClick.setOnClickListener {
            clickTextView(it)
        }

        return binding.root
    }

    fun clickButton(view: View, email: String, username: String, password: String) {
        Snackbar.make(view, "Hesap Oluşturma İşlemini Onaylıyor Musunuz?", Snackbar.LENGTH_SHORT)
            .setAction("Evet") {
                CoroutineScope(Dispatchers.Main).launch {
                    loginPref.createEmail(email)
                    loginPref.createUsername(username)
                    loginPref.createPassword(password)

                    binding.ivSignUp.visibility = View.GONE
                    binding.tvSignUp.visibility = View.GONE
                    binding.tvEmailSignUp.visibility = View.GONE
                    binding.emailSignUpLayout.visibility = View.GONE
                    binding.tvUsernameSignUp.visibility = View.GONE
                    binding.usernameSignUpLayout.visibility = View.GONE
                    binding.tvPasswordSignUp.visibility = View.GONE
                    binding.passwordSignUpLayout.visibility = View.GONE
                    binding.buttonSignUp.visibility = View.GONE
                    binding.tvLoginClick.visibility = View.GONE
                    binding.tvProgressSignUp.visibility = View.VISIBLE
                    binding.progressBarSignUp.visibility = View.VISIBLE

                    delay(2000)

                    Navigation.changePage(view, R.id.action_signUpFragment_to_homePageFragment)
                }
            }
            .show()
    }

    fun clickTextView(view: View) {
        Navigation.changePage(view, R.id.action_signUpFragment_to_loginFragment)
    }
}