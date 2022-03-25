package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.RegistryRequest
import com.example.app.domain.usecases.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistryViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _registryRequest: MutableStateFlow<RegistryRequest> = MutableStateFlow(RegistryRequest.Pending)
    fun registryRequest(): StateFlow<RegistryRequest> = _registryRequest

    fun registryUser(email: String, password: String) {
        viewModelScope.launch {
            _registryRequest.value = saveUserUseCase.invoke(email, password)
        }
    }
}