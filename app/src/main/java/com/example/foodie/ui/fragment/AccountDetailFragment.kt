package com.example.foodie.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.foodie.R
import com.example.foodie.databinding.FragmentAccountDetailBinding
import com.example.foodie.datastore.LoginPref
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountDetailFragment : Fragment() {
    private lateinit var binding: FragmentAccountDetailBinding
    private lateinit var loginPref: LoginPref
    private val bundle: AccountDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_detail, container, false)
        binding.accountDetailFragment = this

        loginPref = LoginPref(requireContext())

        when (bundle.action) {
            "email" -> {
                CoroutineScope(Dispatchers.Main).launch {
                    binding.accountDetailTitle = "Email Değişikliği"
                    binding.isPassword = false
                    binding.currentText = loginPref.readEmail()
                    binding.hintText = "Email"
                }
            }

            "password" -> {
                binding.accountDetailTitle = "Şifre Değişikliği"
                binding.isPassword = true
                binding.hintText = "Şifre"
                binding.passwordEyeDrawable = R.drawable.custom_password_eye
            }
        }

        return binding.root
    }

    fun updateAccount(view: View) {
        when (bundle.action) {
            "email" -> {
                if (binding.editTextNew.text.toString() == "") {
                    binding.layoutNew.error = "Yeni email adresinizi girmelisiniz"
                } else if (binding.editTextNew.text.toString() == binding.editTextAgainNew.text.toString()) {
                    Snackbar.make(view, "Email adresinizi değiştirmek istediğinize emin misiniz?", Snackbar.LENGTH_SHORT)
                        .setAction("Evet") {
                            CoroutineScope(Dispatchers.Main).launch {
                                loginPref.createEmail(binding.editTextNew.text.toString())
                                Snackbar.make(view, "Email adresiniz başarılı bir şekilde değiştirildi", Snackbar.LENGTH_SHORT).show()
                                goToPreviousPage()
                            }
                        }
                        .show()
                } else {
                    binding.layoutAgainNew.error = "Girilen email bilgisi Yeni Email alanındakiyle aynı olmalıdır"
                }
            }

            "password" -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val password = loginPref.readPassword()

                    if (binding.editTextCurrent.text.toString() == "") {
                        binding.layoutCurrent.error = "Mevcut şifrenizi girmelisiniz"
                    } else if (binding.editTextCurrent.text.toString() != password) {
                        binding.layoutCurrent.error = "Girilen şifre bilgisi yanlış"
                    } else if (binding.editTextNew.text.toString() == "") {
                        binding.layoutNew.error = "Yeni şifrenizi girmelisiniz"
                    } else if (binding.editTextNew.text.toString() == binding.editTextAgainNew.text.toString()) {
                        Snackbar.make(view, "Şifrenizi değiştirmek istediğinize emin misiniz?", Snackbar.LENGTH_SHORT)
                            .setAction("Evet") {
                                CoroutineScope(Dispatchers.Main).launch {
                                    loginPref.createPassword(binding.editTextNew.text.toString())
                                    Snackbar.make(view, "Şifreniz başarılı bir şekilde değiştirildi", Snackbar.LENGTH_SHORT).show()
                                    goToPreviousPage()
                                }
                            }
                            .show()
                    } else {
                        binding.layoutAgainNew.error = "Girilen şifreniz Yeni Şifre alanındakiyle aynı olmalıdır"
                    }
                }
            }
        }
    }

    fun goToPreviousPage() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun errorDisable() {
        binding.isErrorEnabled = false
    }
}