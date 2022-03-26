package com.example.app.ui

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.databinding.LogInFragmentLayoutBinding
import com.example.app.domain.entities.LogInRequest
import com.example.app.presentation.viewmodels.LogInViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel by viewModels<LogInViewModel>()

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
        binding.password.filters = arrayOf(filter)
    }

    private fun initListeners() {

        with(binding) {
            changePassword.setOnClickListener {
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToChangePasswordFragment())
            }

            enter.setOnClickListener {
                if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                    showToast(getString(R.string.fields_are_not_filled))
                } else {
                    progressBar.visibility = View.VISIBLE
                    enter.visibility = View.GONE
                    emailLabel.visibility = View.GONE
                    passwordLabel.visibility = View.GONE
                    registry.visibility = View.GONE
                    changePassword.visibility = View.GONE
                    viewModel.checkUser(email.text.toString(), password.text.toString())
                }
            }

            registry.setOnClickListener {
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegistryFragment())
            }

        }
    }

    private fun initObservers() {
        with(binding) {
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
                            with(binding) {
                                email.setText("")
                                password.setText("")
                            }
                        }
                        is LogInRequest.IncorrectPassword -> showToast(getString(R.string.incorrect_password))
                        is LogInRequest.UserNotFound -> showToast(getString(R.string.user_not_found))
                    }
                    progressBar.visibility = View.GONE
                    emailLabel.visibility = View.VISIBLE
                    enter.visibility = View.VISIBLE
                    passwordLabel.visibility = View.VISIBLE
                    registry.visibility = View.VISIBLE
                    changePassword.visibility = View.VISIBLE
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