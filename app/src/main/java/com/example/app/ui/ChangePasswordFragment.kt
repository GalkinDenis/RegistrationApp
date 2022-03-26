package com.example.app.ui

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.app.R
import com.example.app.databinding.ChangePasswordFragmentLayoutBinding
import com.example.app.domain.entities.ChangePasswordRequest
import com.example.app.presentation.viewmodels.ChangePasswordViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {

    private val viewModel by viewModels<ChangePasswordViewModel>()

    private var _binding: ChangePasswordFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangePasswordFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInputFilters()
        initListeners()
        initObservers()
    }

    private fun setInputFilters() {
        val filter = InputFilter { src, start, end, _, _, _ ->
            for (i in start until end) {
                if (!Character.isLetter(src[i]) && !Character.isDigit(src[i])) {
                    return@InputFilter src.subSequence(start, i)
                }
            }
            null
        }
        with(binding) {
            oldPassword.filters = arrayOf(filter)
            password.filters = arrayOf(filter)
            secondPassword.filters = arrayOf(filter)
        }
    }

    private fun initListeners() {
        with(binding) {
            changePasswordButton.setOnClickListener {
                val email = email.text.toString()
                val oldPassword = oldPassword.text.toString()
                val newPassword = password.text.toString()
                val newSecondPassword = secondPassword.text.toString()
                when {
                    (email.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || newSecondPassword.isEmpty()) -> {
                        showToast(getString(R.string.fields_are_not_filled))
                    }
                    newPassword != newSecondPassword -> showToast(getString(R.string.password_do_not_match))
                    else -> {
                        progressBar.visibility = View.VISIBLE
                        emailLabel.visibility = View.GONE
                        oldPasswordLabel.visibility = View.GONE
                        passwordLabel.visibility = View.GONE
                        secondPasswordLabel.visibility = View.GONE
                        changePasswordButton.visibility = View.GONE
                        viewModel.changePassword(email, oldPassword, newPassword)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.changePasswordRequest().observe(viewLifecycleOwner) { changePasswordResponse ->
                with(binding) {
                    progressBar.visibility = View.GONE
                    emailLabel.visibility = View.VISIBLE
                    oldPasswordLabel.visibility = View.VISIBLE
                    passwordLabel.visibility = View.VISIBLE
                    secondPasswordLabel.visibility = View.VISIBLE
                    changePasswordButton.visibility = View.VISIBLE
                }
                when (changePasswordResponse) {
                    is ChangePasswordRequest.Pending -> return@observe
                    is ChangePasswordRequest.PasswordChanged -> showToast(getString(R.string.password_changed))
                    is ChangePasswordRequest.OldPasswordIsNotCorrect -> showToast(getString(R.string.incorrect_old_password))
                    is ChangePasswordRequest.UserNotFound -> showToast(getString(R.string.user_not_found))
                }
            }
        }
    }

    private fun showToast(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}