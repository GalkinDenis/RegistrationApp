package com.example.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.app.databinding.ChangePasswordFragmentLayoutBinding
import com.example.app.di.App
import com.example.app.presentation.viewmodels.ChangePasswordViewModel
import javax.inject.Inject

class ChangePasswordFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ChangePasswordViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.injectChangePasswordFragment(this)
    }

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


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}