package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSignUpBinding
import com.example.foodie.datastore.LoginPref
import com.example.foodie.ui.viewmodel.SignUpViewModel
import com.example.foodie.utils.changePage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    @Inject lateinit var loginPref: LoginPref
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.bg_page)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.signUpFragment = this
        binding.isSignUp = false

        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            when (userData[0]) {
                "" -> {
                    Snackbar.make(binding.tvSignUp, "Lütfen Tüm Alanları Doldurunuz!", Snackbar.LENGTH_SHORT).show()
                }

                else -> {
                    Snackbar.make(binding.tvSignUp, "Hesap Oluşturma İşlemini Onaylıyor Musunuz?", Snackbar.LENGTH_SHORT)
                        .setAction("Evet") {
                            CoroutineScope(Dispatchers.Main).launch {
                                loginPref.createEmail(userData[0])
                                loginPref.createUsername(userData[1])
                                loginPref.createPassword(userData[2])

                                binding.isSignUp = true
                                delay(2000)
                                Navigation.changePage(binding.tvSignUp, R.id.action_signUpFragment_to_homePageFragment)
                            }
                        }
                        .show()
                }
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SignUpViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun clickButton(view: View, email: String, username: String, password: String) {
        viewModel.signUp(email, username, password)
    }

    fun clickTextView(view: View) {
        Navigation.changePage(view, R.id.action_signUpFragment_to_loginFragment)
    }
}