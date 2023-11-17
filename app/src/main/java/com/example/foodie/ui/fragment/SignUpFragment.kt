package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
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
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.signUpFragment = this
        binding.isSignUp = false

        loginPref = LoginPref(requireContext())

        return binding.root
    }

    fun clickButton(view: View, email: String, username: String, password: String) {
        if (email != "" && username != "" && password != "") {
            Snackbar.make(view, "Hesap Oluşturma İşlemini Onaylıyor Musunuz?", Snackbar.LENGTH_SHORT)
                .setAction("Evet") {
                    CoroutineScope(Dispatchers.Main).launch {
                        loginPref.createEmail(email)
                        loginPref.createUsername(username)
                        loginPref.createPassword(password)

                        binding.isSignUp = true
                        delay(2000)
                        Navigation.changePage(view, R.id.action_signUpFragment_to_homePageFragment)
                    }
                }
                .show()
        } else {
            Snackbar.make(view, "Lütfen Tüm Alanları Doldurunuz!", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun clickTextView(view: View) {
        Navigation.changePage(view, R.id.action_signUpFragment_to_loginFragment)
    }
}