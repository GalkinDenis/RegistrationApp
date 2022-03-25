package com.example.app.ui

import android.content.Context
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.databinding.RegistryFragmentLayoutBinding
import com.example.app.di.App
import com.example.app.presentation.viewmodels.RegistryViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class RegistryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RegistryViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.injectRegistryFragment(this)
    }

    private var _binding: RegistryFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistryFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            password.keyListener = DigitsKeyListener.getInstance(VALID_CHARACTERS)
            secondPassword.keyListener = DigitsKeyListener.getInstance(VALID_CHARACTERS)

            registryButton.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                val secondPassword = secondPassword.text.toString()
                when {
                    (email.isEmpty() || password.isEmpty() || secondPassword.isEmpty()) -> showToast(
                        R.string.fields_are_not_filled.toString()
                    )
                    password != secondPassword -> showToast(R.string.password_do_not_match.toString())
                    else -> viewModel.registryUser(email, password)
                }
            }

        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.registryRequest().collect { loginResponse ->
                when (loginResponse) {
                    is Response.Pending -> return@collect
                    is Response.UserFound -> {
                        findNavController().navigate(
                            LogInFragmentDirections.actionLogInFragmentToAboutFragment(
                                loginResponse.email,
                                loginResponse.password
                            )
                        )
                    }
                    is Response.IncorrectPassword -> showToast(R.string.incorrect_password.toString())
                    is Response.UserNotFound -> showToast(R.string.user_not_found.toString())
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