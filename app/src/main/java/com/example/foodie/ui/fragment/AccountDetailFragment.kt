package com.example.foodie.ui.fragment

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import com.example.foodie.R
import com.example.foodie.databinding.FragmentAccountDetailBinding
import com.example.foodie.datastore.LoginPref
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
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
        binding = FragmentAccountDetailBinding.inflate(inflater, container, false)

        binding.ivAccountBack.setOnClickListener {
            goToPreviousPage()
        }

        loginPref = LoginPref(requireContext())

        binding.editTextNew.addTextChangedListener {
            binding.layoutNew.error = null
        }

        binding.editTextAgainNew.addTextChangedListener {
            binding.layoutAgainNew.error = null
        }

        when (bundle.action) {
            "email" -> {
                CoroutineScope(Dispatchers.Main).launch {
                    binding.tvAcoountDetailTitle.text = "Email Değişikliği"
                    binding.layoutCurrent.isEnabled = false

                    binding.editTextCurrent.setText(loginPref.readEmail())
                    binding.layoutCurrent.hint = "Mevcut Email"
                    binding.layoutNew.hint = "Yeni Email"
                    binding.layoutAgainNew.hint = "Tekrar Yeni Email"
                }
            }

            "password" -> {
                binding.tvAcoountDetailTitle.text = "Şifre Değişikliği"

                binding.editTextCurrent.addTextChangedListener {
                    binding.layoutCurrent.error = null
                }

                binding.layoutCurrent.hint = "Mevcut Şifre"
                binding.editTextCurrent.transformationMethod = PasswordTransformationMethod.getInstance()

                binding.layoutNew.hint = "Yeni Şifre"
                binding.editTextNew.transformationMethod = PasswordTransformationMethod.getInstance()

                binding.layoutAgainNew.hint = "Tekrar Yeni Şifre"
                binding.editTextAgainNew.transformationMethod = PasswordTransformationMethod.getInstance()

                binding.layoutCurrent.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                binding.layoutCurrent.setEndIconDrawable(R.drawable.custom_password_eye)

                binding.layoutNew.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                binding.layoutNew.setEndIconDrawable(R.drawable.custom_password_eye)

                binding.layoutAgainNew.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                binding.layoutAgainNew.setEndIconDrawable(R.drawable.custom_password_eye)
            }
        }

        binding.buttonUpdateAccount.setOnClickListener {
            updateAccount(it)
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
}