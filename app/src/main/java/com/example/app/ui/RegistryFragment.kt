package com.example.app.ui

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.app.R
import com.example.app.databinding.RegistryFragmentLayoutBinding
import com.example.app.di.App
import com.example.app.domain.entities.UserRegistrationRequest
import com.example.app.presentation.viewmodels.RegistrationViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class RegistryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RegistrationViewModel> { viewModelFactory }

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
            password.filters = arrayOf(filter)
            secondPassword.filters = arrayOf(filter)
        }
    }

    private fun initListeners() {
        with(binding) {
            registryButton.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                val secondPassword = secondPassword.text.toString()
                when {
                    (email.isEmpty() || password.isEmpty() || secondPassword.isEmpty()) -> {
                        showToast(getString(R.string.fields_are_not_filled))
                    }
                    password != secondPassword -> showToast(getString(R.string.password_do_not_match))
                    else -> {
                        progressBar.visibility = View.VISIBLE
                        emailLabel.visibility = View.GONE
                        registryButton.visibility = View.GONE
                        secondPasswordLabel.visibility = View.GONE
                        passwordLabel.visibility = View.GONE
                        viewModel.registrationUser(email, password)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.registrationRequest().observe(viewLifecycleOwner) { loginResponse ->
                with(binding) {
                    progressBar.visibility = View.GONE
                    emailLabel.visibility = View.VISIBLE
                    registryButton.visibility = View.VISIBLE
                    secondPasswordLabel.visibility = View.VISIBLE
                    passwordLabel.visibility = View.VISIBLE
                }
                when (loginResponse) {
                    is UserRegistrationRequest.Pending -> return@observe
                    is UserRegistrationRequest.UserSaved -> showToast(getString(R.string.registration_success))
                    is UserRegistrationRequest.AlreadyExist -> showToast(getString(R.string.user_already_exist))
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