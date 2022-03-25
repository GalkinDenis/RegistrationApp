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
import com.example.app.databinding.LogInFragmentLayoutBinding
import com.example.app.di.App
import com.example.app.domain.LogInRequest
import com.example.app.presentation.viewmodels.LogInViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LogInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LogInViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.injectLogInFragment(this)
    }

    private var _binding: LogInFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LogInFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.email.setText("")
        binding.password.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {

            password.keyListener = DigitsKeyListener.getInstance(VALID_CHARACTERS)
            changePassword.setOnClickListener {
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToChangePasswordFragment())
            }

            enter.setOnClickListener {
                if(email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                    showToast(getString(R.string.fields_are_not_filled))
                } else {
                    viewModel.checkUser(email.text.toString(), password.text.toString())
                }
            }

            registry.setOnClickListener {
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegistryFragment())
            }

        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginRequest().observe(viewLifecycleOwner) { loginResponse ->
                when (loginResponse) {
                    is LogInRequest.Pending -> return@observe
                    is LogInRequest.UserFound -> {
                        findNavController().navigate(
                            LogInFragmentDirections.actionLogInFragmentToAboutFragment(
                                loginResponse.email
                            )
                        )
                    }
                    is LogInRequest.IncorrectPassword -> showToast(getString(R.string.incorrect_password))
                    is LogInRequest.UserNotFound -> showToast(getString(R.string.user_not_found))
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